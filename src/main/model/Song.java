package model;

import exceptions.*;

/*
 * represents a song by an artist in a genre, with a lyrics, production, and vocals rating 
 * initialized at 1. boolean isFavourite is also initialized as false. calculateTotalScore()
 * calculates the average score for all songs based on the three ratings. setLyricsRating,
 * setProdRating, and setVocals updates the respective rating if it is valid. if the input
 * is not valid, the methods throw the InvalidRating exception.
 */
public class Song {
    private String title;
    private String artist;
    private String genre;
    private int lyricsRating;
    private int prodRating;
    private int vocalsRating;
    private boolean isFavourite;
    
    /* 
     * REQUIRES: lyricsRating must be an integer from 1-5 inclusive.
     *           productionRating must be an integer from 1-5 inclusive.
     *           vocalsRating must be an integer from 1-5 inclusive.
     * EFFECTS: creates a song with a title, artist, genre, and
     *          a lyricsRating, productionRating, and vocalsRating
     *          initialized at 1.
    */ 
    public Song(String songTitle, String songArtist, String songGenre) {
        title = songTitle;
        artist = songArtist;
        genre = songGenre;
        this.lyricsRating = 1;
        this.prodRating = 1;
        this.vocalsRating = 1;
        isFavourite = false;
    }

    /*
     * REQUIRES: lyricsRating must be an integer from 1-5 inclusive.
     *           productionRating must be an integer from 1-5 inclusive.
     *           vocalsRating must be an integer from 1-5 inclusive.
     * EFFECTS: calculates total average score of a song rounded to 2 decimal places
     */
    public double calculateTotalScore() {
        return (lyricsRating + prodRating + vocalsRating) / 3;
    }

    // MODIFIES: this
    // EFFECTS: if lyricsRating is between 1-5 inclusive, updates the song's rating
    //          otherwise, throw InvalidRating exception
    public void setLyricsRating(int lyricsRating) throws InvalidRating {
        if (lyricsRating >= 1 && lyricsRating <= 5) {
            this.lyricsRating = lyricsRating;
        } else {
            throw new InvalidRating();
        }
    }

    // MODIFIES: this
    // EFFECTS: if vocalsRating is between 1-5 inclusive, updates the song's rating
    //          otherwise, throw InvalidRating exception
    public void setProdRating(int prodRating) throws InvalidRating {
        if (prodRating >= 1 && prodRating <= 5) {
            this.prodRating = prodRating;
        } else {
            throw new InvalidRating();
        }
    }
    
    // MODIFIES: this
    // EFFECTS: if vocalsRating is between 1-5 inclusive, updates the song's rating
    //          otherwise, throw InvalidRating exception
    public void setVocalsRating(int vocalsRating) throws InvalidRating {
        if (vocalsRating >= 1 && vocalsRating <= 5) {
            this.vocalsRating = vocalsRating;
        } else {
            throw new InvalidRating();
        }
    }

    public void setIsFavourite(boolean input) {
        this.isFavourite = input;
    }

    public boolean getIsFavourite() {
        return isFavourite;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public int getLyricsRating() {
        return lyricsRating;
    }

    public int getProdRating() {
        return prodRating;
    }

    public int getVocalsRating() {
        return vocalsRating;
    }
}
