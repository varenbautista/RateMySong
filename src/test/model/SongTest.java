package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InvalidRating;

public class SongTest {
    private Song testSong1;
    private Song testSong2;
    private Song testSong3;

    @BeforeEach
    void runBefore() {
        testSong1 = new Song("Happiness is a Butterfly", "Lana Del Rey", "Alt Rock");
        testSong2 = new Song("Back to December", "Taylor Swift", "Country");
        testSong3 = new Song("Vroom Vroom", "Charli XCX", "Hyperpop");
    }

    @Test
    void testConstructor() {
        assertEquals("Happiness is a Butterfly", testSong1.getTitle());
        assertEquals("Lana Del Rey", testSong1.getArtist());
        assertEquals("Alt Rock", testSong1.getGenre());
        assertEquals(1, testSong1.getLyricsRating());
        assertEquals(1, testSong1.getProdRating());
        assertEquals(1, testSong1.getVocalsRating());

        assertEquals("Back to December", testSong2.getTitle());
        assertEquals("Taylor Swift", testSong2.getArtist());
        assertEquals("Country", testSong2.getGenre());
        assertEquals(1, testSong2.getLyricsRating());
        assertEquals(1, testSong2.getProdRating());
        assertEquals(1, testSong2.getVocalsRating());

        assertEquals("Vroom Vroom", testSong3.getTitle());
        assertEquals("Charli XCX", testSong3.getArtist());
        assertEquals("Hyperpop", testSong3.getGenre());
        assertEquals(1, testSong3.getLyricsRating());
        assertEquals(1, testSong3.getProdRating());
        assertEquals(1, testSong3.getVocalsRating());
    }

    @Test
    void testGetSetIsFavourite() {
        assertFalse(testSong1.getIsFavourite());
        testSong1.setIsFavourite(true);
        assertTrue(testSong1.getIsFavourite());

        assertFalse(testSong2.getIsFavourite());
        testSong2.setIsFavourite(true);
        testSong2.setIsFavourite(true);
        assertTrue(testSong2.getIsFavourite());

        assertFalse(testSong3.getIsFavourite());
        testSong3.setIsFavourite(true);
        testSong3.setIsFavourite(false);
        testSong3.setIsFavourite(true);
        assertTrue(testSong3.getIsFavourite());
    }

    @Test
    void testCalculateTotalScore() {
        assertEquals(1, testSong1.calculateTotalScore());
        assertEquals(1, testSong2.calculateTotalScore());
        assertEquals(1, testSong3.calculateTotalScore());
    }

    @Test
    void testSetLyricsRatingNothingThrown() {
        try {
            testSong1.setLyricsRating(5);
            assertEquals(5, testSong1.getLyricsRating());

            testSong1.setLyricsRating(3);
            assertEquals(3, testSong1.getLyricsRating());

            testSong1.setLyricsRating(1);
            assertEquals(1, testSong1.getLyricsRating());
        } catch (InvalidRating e) {
            fail("Expected nothing thrown got InvalidRating instead");
        }
    }

    @Test
    void testSetLyricsRatingInvalidRatingThrown() {
        try {
            testSong1.setLyricsRating(0);
            fail("InvalidRating not thrown");

            testSong1.setLyricsRating(6);
            fail("InvalidRating not thrown");
            
            testSong1.setLyricsRating(-1);
            fail("InvalidRating not thrown");

            testSong1.setLyricsRating(10);
            fail("InvalidRating not thrown");
        } catch (InvalidRating e) {
            // expected; handle in ui package
        }
    }

    @Test
    void testSetProdRatingNothingThrown() {
        try {
            testSong2.setProdRating(5);
            assertEquals(5, testSong2.getProdRating());

            testSong2.setProdRating(3);
            assertEquals(3, testSong2.getProdRating());

            testSong2.setProdRating(1);
            assertEquals(1, testSong2.getProdRating());
        } catch (InvalidRating e) {
            fail("Expected nothing thrown got InvalidRating instead");
        }
    }

    @Test
    void testSetProdRatingInvalidRatingThrown() {
        try {
            testSong2.setProdRating(0);
            fail("InvalidRating not thrown");

            testSong2.setProdRating(6);
            fail("InvalidRating not thrown");
            
            testSong2.setProdRating(-1);
            fail("InvalidRating not thrown");

            testSong2.setProdRating(10);
            fail("InvalidRating not thrown");
        } catch (InvalidRating e) {
            // expected; handle in ui package
        }
    }

    @Test
    void testSetVocalsRatingNothingThrown() {
        try {
            testSong3.setVocalsRating(5);
            assertEquals(5, testSong3.getVocalsRating());

            testSong3.setVocalsRating(3);
            assertEquals(3, testSong3.getVocalsRating());

            testSong3.setVocalsRating(1);
            assertEquals(1, testSong3.getVocalsRating());
        } catch (InvalidRating e) {
            fail("Expected nothing thrown got InvalidRating instead");
        }
    }

    @Test
    void testSetVocalsRatingInvalidRatingThrown() {
        try {
            testSong3.setVocalsRating(0);
            fail("InvalidRating not thrown");

            testSong3.setVocalsRating(6);
            fail("InvalidRating not thrown");
            
            testSong3.setVocalsRating(-1);
            fail("InvalidRating not thrown");

            testSong3.setVocalsRating(10);
            fail("InvalidRating not thrown");
        } catch (InvalidRating e) {
            // expected; handle in ui package
        }
    }
}
