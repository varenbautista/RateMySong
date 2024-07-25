package persistence;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Song;
import model.SongLibrary;

// Adapted from JsonSerializationDemo
public class JsonWriterTest extends JsonTest {
    
    @Test
    void testWriterInvalidFile() {
        try {
            SongLibrary sl = new SongLibrary();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyLibrary() {
        try {
            SongLibrary sl = new SongLibrary();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLibrary.json");
            writer.open();
            writer.write(sl);
            writer.close();
    
            JsonReader reader = new JsonReader("./data/testWriterEmptyLibrary.json");
            sl = reader.read();
            assertEquals(0, sl.getSongCount());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLibrary() {
        try {
            SongLibrary sl = new SongLibrary();
            sl.addSong(new Song("Happiness is a Butterfly", "Lana Del Rey", "Alt Rock"));
            sl.addSong(new Song("Back to December", "Taylor Swift", "Country"));
            sl.addSong(new Song("Vroom Vroom", "Charli XCX","Hyperpop"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLibrary");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLibrary.json");
            sl = reader.read();
            checkSong(sl.getIndex(0), "Happiness is a Butterfly", "Lana Del Rey", "Alt Rock");
            assertEquals(5, sl.getIndex(0).getLyricsRating());
            assertEquals(5, sl.getIndex(0).getProdRating());
            assertEquals(5, sl.getIndex(0).getVocalsRating());
            assertTrue(sl.getIndex(0).getIsFavourite());

            checkSong(sl.getIndex(1), "Back to December", "Taylor Swift", "Country");
            assertTrue(sl.getIndex(1).getIsFavourite());

            checkSong(sl.getIndex(2), "Vroom Vroom", "Charli XCX", "Hyperpop");
            assertFalse(sl.getIndex(2).getIsFavourite());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLibraryFindSong() {
        try {
            SongLibrary sl = new SongLibrary();
            sl.addSong(new Song("Happiness is a Butterfly", "Lana Del Rey", "Alt Rock"));
            sl.addSong(new Song("Back to December", "Taylor Swift", "Country"));
            sl.addSong(new Song("Vroom Vroom", "Charli XCX","Hyperpop"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLibrary");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLibrary.json");
            sl = reader.read();
            
            assertEquals(sl.getIndex(0), sl.findSong("Happiness is a Butterfly"));
            assertEquals(null, sl.findSong("Nonexistent song"));
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLibraryGetSortedSongs() {
        try {
            SongLibrary sl = new SongLibrary();
            sl.addSong(new Song("Back to December", "Taylor Swift", "Country"));
            sl.addSong(new Song("Happiness is a Butterfly", "Lana Del Rey", "Alt Rock"));
            sl.addSong(new Song("Vroom Vroom", "Charli XCX","Hyperpop"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLibrary");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLibrary.json");
            sl = reader.read();
            
            List<Song> sortedSongs = sl.getSortedSongs();
            assertEquals("Happiness is a Butterfly", sortedSongs.get(0).getTitle());
            assertEquals("Back to December", sortedSongs.get(1).getTitle());
            assertEquals("Vroom Vroom", sortedSongs.get(2).getTitle());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
