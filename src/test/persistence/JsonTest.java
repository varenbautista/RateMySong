package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import model.Song;

// Adapted from JsonSerializationDemo
public class JsonTest {
    protected void checkSong(Song song, String title, String artist, String genre) {
        assertEquals(title, song.getTitle());
        assertEquals(artist, song.getArtist());
        assertEquals(genre, song.getGenre());
    }
}
