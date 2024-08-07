package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import exceptions.InvalidRating;
import model.*;

// Adapted from JsonSerializationDemo
// Represents a reader that reads songLibrary from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }
    
    // EFFECTS: reads songLibrary from file and returns it
    //          throws IOException if an error occurs when reading data from file
    public SongLibrary read()  throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);

        EventLog.getInstance().logEvent(new Event ("Music Library loaded"));

        return parseSongLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses songLibrary from JSON object and returns it
    private SongLibrary parseSongLibrary(JSONObject jsonObject) {
        SongLibrary sl = new SongLibrary();
        addSongs(sl, jsonObject);
        return sl;
    }

    // MODIFIES: sl
    // EFFECTS: parses songs from JSON object and adds them to songLibrary
    private void addSongs(SongLibrary sl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("library");
        for (Object json : jsonArray) {
            JSONObject nextSong = (JSONObject) json;
            addSong(sl, nextSong);
        }
    }

    // MODIFIES: sl
    // EFFECTS: parses song from JSON object and adds it to songLibrary
    private void addSong(SongLibrary sl, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String artist = jsonObject.getString("artist");
        String genre = jsonObject.getString("genre");
        Song song = new Song(title, artist, genre);

        int lyricsRating = jsonObject.getInt("lyrics rating");
        int prodRating = jsonObject.getInt("production rating");
        int vocalsRating = jsonObject.getInt("vocals rating");
        boolean isFavourite = jsonObject.getBoolean("is favourite");

        try {
            song.setLyricsRating(lyricsRating);
            song.setProdRating(prodRating);
            song.setVocalsRating(vocalsRating);
            song.setIsFavourite(isFavourite);
        } catch (InvalidRating e) {
            // handle in ui package
        }

        sl.addSong(song);
    }

}