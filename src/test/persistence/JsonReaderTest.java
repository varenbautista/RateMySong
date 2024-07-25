package persistence;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import model.SongLibrary;

// Adapted from JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            SongLibrary sl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLibrary.json");
        try {
            SongLibrary sl = reader.read();
            assertEquals(0, sl.getSongCount());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    

    @Test
    void testReaderGeneralLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLibrary.json");
        try {
            SongLibrary sl = reader.read();
            assertEquals(3, sl.getSongCount());
            checkSong(sl.getIndex(0), "Happiness is a Butterfly", "Lana Del Rey", "Alt Rock");
            checkSong(sl.getIndex(1), "Back to December", "Taylor Swift", "Country");
            checkSong(sl.getIndex(2), "Vroom Vroom", "Charli XCX", "Hyperpop");
        } catch (IOException e) {
            fail("Couldn't read form file");
        }
    }
}
