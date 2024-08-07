package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

/*
 * represents a library of songs (list of songs). the library is intialized as empty.
 * addSong adds a song to the library, getSongCount returns the number of songs in the library,
 * and getIndex returns the song at a given index. getSortedSongs sorts the songs in ascending
 * index from greatest to lowest overall score. findSong searches the library and returns
 * the song whose title matches the inputted String.
 */
public class SongLibrary implements Writable {
    private List<Song> library;

    // EFFECTS: creates an empty song library
    public SongLibrary() {
        this.library = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a song to the library
    public void addSong(Song song) {
        library.add(song);
        EventLog.getInstance().logEvent(new Event (song.getTitle() + " added to music library"));
    }

    // MODIFIES: this
    // EFFECTS: removes a song from the library
    public void removeSong(Song song) {
        library.remove(song);
        EventLog.getInstance().logEvent(new Event (song.getTitle() + " removed from music library"));
    }

    // EFFECTS: returns the number of songs in the library
    public int getSongCount() {
        return library.size();
    }

    // EFFECTS: returns the song at the given index of the library
    public Song getIndex(int num) {
        return library.get(num);
    }
    
    // EFFECTS: returns a list of songs sorted from greatest to smallest total score
    public List<Song> getSortedSongs() {
        List<Song> sortedSongs = new ArrayList<>(library);
        int n = sortedSongs.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedSongs.get(j).calculateTotalScore() < sortedSongs.get(j + 1).calculateTotalScore()) {
                    Song newSong = sortedSongs.get(j);
                    sortedSongs.set(j, sortedSongs.get(j + 1));
                    sortedSongs.set(j + 1, newSong);
                }
            }
        }
        EventLog.getInstance().logEvent(new Event ("Music library has been sorted"));
        return sortedSongs;
    }

    // EFFECTS: searchs the song library and returns the song that matches the given String
    public Song findSong(String title) {
        for (Song song : library) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("library", libraryToJson());
        return json;
    }

    // EFFECTS: returns songs in the song library as a JSON array
    private JSONArray libraryToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song s : library) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }
}
