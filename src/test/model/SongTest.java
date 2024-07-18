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

            testSong2.setLyricsRating(5);
            testSong2.setLyricsRating(4);
            assertEquals(4, testSong2.getLyricsRating());

            testSong3.setLyricsRating(4);
            testSong3.setLyricsRating(4);
            assertEquals(4, testSong3.getLyricsRating());
        } catch (InvalidRating e) {
            fail("Expected nothing thrown got InvalidRating instead");
        }
    }

    @Test
    void testSetLyricsRatingInvalidRatingThrown() {
        try {
            testSong1.setLyricsRating(6);
            fail("InvalidRating not thrown");
        } catch (InvalidRating e) {
            // expected; handle in ui package
        }
    }

    @Test
    void testSetProdRatingNothingThrown() {
        try {
            testSong1.setProdRating(5);
            assertEquals(5, testSong1.getProdRating());

            testSong2.setProdRating(5);
            testSong2.setProdRating(4);
            assertEquals(4, testSong2.getProdRating());

            testSong3.setProdRating(4);
            testSong3.setProdRating(4);
            assertEquals(4, testSong3.getProdRating());
        } catch (InvalidRating e) {
            fail("Expected nothing thrown got InvalidRating instead");
        }
    }

    @Test
    void testSetProdRatingInvalidRatingThrown() {
        try {
            testSong1.setProdRating(6);
            fail("InvalidRating not thrown");
        } catch (InvalidRating e) {
            // expected; handle in ui package
        }
    }

    @Test
    void testSetVocalsRatingNothingThrown() {
        try {
            testSong1.setVocalsRating(5);
            assertEquals(5, testSong1.getVocalsRating());

            testSong2.setVocalsRating(5);
            testSong2.setVocalsRating(4);
            assertEquals(4, testSong2.getVocalsRating());

            testSong3.setVocalsRating(4);
            testSong3.setVocalsRating(4);
            assertEquals(4, testSong3.getVocalsRating());
        } catch (InvalidRating e) {
            fail("Expected nothing thrown got InvalidRating instead");
        }
    }

    @Test
    void testSetVocalsRatingInvalidRatingThrown() {
        try {
            testSong1.setVocalsRating(6);
            fail("InvalidRating not thrown");
        } catch (InvalidRating e) {
            // expected; handle in ui package
        }
    }
}
