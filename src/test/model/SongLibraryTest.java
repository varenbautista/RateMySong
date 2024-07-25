package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.*;

import exceptions.InvalidRating;

public class SongLibraryTest {
    private Song testSong1;
    private Song testSong2;
    private Song testSong3;
    private SongLibrary library;

    @BeforeEach
    void runBefore() throws InvalidRating {
        testSong1 = new Song("Happiness is a Butterfly", "Lana Del Rey", "Alt Rock");
        testSong1.setLyricsRating(5);
        testSong1.setProdRating(5);
        testSong1.setLyricsRating(5);

        testSong2 = new Song("Back to December", "Taylor Swift", "Country");
        testSong2.setLyricsRating(1);
        testSong2.setProdRating(1);
        testSong2.setLyricsRating(1);

        testSong3 = new Song("Vroom Vroom", "Charli XCX", "Hyperpop");
        testSong3.setLyricsRating(3);
        testSong3.setProdRating(3);
        testSong3.setLyricsRating(3);
        library = new SongLibrary();
    }

    @Test
    void testConstructor() {
        assertEquals(0, library.getSongCount());
    }

    @Test
    void testAddSong() {
        library.addSong(testSong1);
        assertEquals(testSong1, library.getIndex(0));

        library.addSong(testSong2);
        library.addSong(testSong3);
        assertEquals(testSong1, library.getIndex(0));
        assertEquals(testSong2, library.getIndex(1));
        assertEquals(testSong3, library.getIndex(2));
    }

    @Test
    void testGetSongCount() {
        library.addSong(testSong1);
        assertEquals(1, library.getSongCount());

        library.addSong(testSong2);
        library.addSong(testSong3);
        assertEquals(3, library.getSongCount());
    }

    @Test
    void testGetSortedSongs() {
        library.addSong(testSong3);
        library.addSong(testSong2);
        library.addSong(testSong1);

        List<Song> sortedSongs = library.getSortedSongs();
        assertEquals(testSong1, sortedSongs.get(0));
        assertEquals(testSong3, sortedSongs.get(1));
        assertEquals(testSong2, sortedSongs.get(2));

    }

    @Test
    void testFindSong() {
        library.addSong(testSong1);
        library.addSong(testSong2);
        library.addSong(testSong3);

        assertEquals(testSong1, library.findSong("happiness is a butterfly"));
        assertEquals(testSong2, library.findSong("BACK TO DECEMBER"));
        assertEquals(testSong3, library.findSong("vrOOm vRooM"));
        assertEquals(null, library.findSong("klasjdflaksdj"));
    }
}
