package ui;

import java.util.Scanner;
import exceptions.InvalidRating;
import model.*;

// RateMySong application
public class RateMySongApp {
    private SongLibrary library;
    private Scanner scanner;
    private boolean isRunning;


    // EFFECTS: runs the application
    public RateMySongApp() {
        init();
        System.out.println("Welcome to RateMySong!");
        while (this.isRunning) {
            handleMenu();
        }
    }

    // EFFECTS: displays and processes input for the main menu
    private void handleMenu() {
        displayMenu();
        String input = this.scanner.nextLine();
        processCommand(input);
    }

    // MODIFIES: this
    // EFFECTS: initializes the application with an empty music library
    private void init() {
        this.library = new SongLibrary();
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
    }

    // EFFECTS: displays a list of commands
    private void displayMenu() {
        System.out.println("Please select an option:\n");
        System.out.println("\ta -> add a song to your music library");
        System.out.println("\tv -> view your music libary");
        System.out.println("\ts -> search for a song");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFFECTS: processes user input
    private void processCommand(String input) {
        switch (input) {
            case "a":
                addSong();
                break;
            case "v":
                viewSongs();
                break;
            case "s":
                searchForSong();
                break;
            case "q":
                quitApp();
                break;
            default:
                System.out.println("Invalid option! Please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a song to the music library
    private void addSong() {
        System.out.println("Please enter the song's title:");
        String title = scanner.nextLine();

        System.out.println("Please enter the song's  artist:");
        String artist = scanner.nextLine();

        System.out.println("Please enter the song's genre:");
        String genre = scanner.nextLine();

        Song song = new Song(title, artist, genre);

        setFavourite(song);
        setRating(song, "lyrics");
        setRating(song, "production");
        setRating(song, "vocals");

        library.addSong(song);
        System.out.println("New song " + song.getTitle() 
                            + " added with a total score of: " + song.calculateTotalScore());
    }

    // MODIFIES: this
    // EFFECTS: sets favourite status when initializing a song
    private void setFavourite(Song song) {
        boolean isValid = false;
        while (!isValid) {
            System.out.println("Is this your favourite song? (yes/no)");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("yes")) {
                song.setIsFavourite(true);
                isValid = true;
            } else if (input.equalsIgnoreCase("no")) {
                song.setIsFavourite(false);
                isValid = true;
            } else {
                System.out.println("Invalid! Please try again.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the rating of the song based on the type of rating
    private void setRating(Song song, String type) {
        boolean isValidRating = false;
        int rating = 0;
        while (!isValidRating) {
            System.out.printf("Please rate the song's %s on a scale from 1 to 5:%n", type);
            rating = scanner.nextInt();
            try {
                switch (type) {
                    case "lyrics":
                        song.setLyricsRating(rating);
                        break;
                    case "production":
                        song.setProdRating(rating);
                        break;
                    case "vocals":
                        song.setVocalsRating(rating);
                        break;
                }
                isValidRating = true;
                System.out.println("Rating updated!\n");
            } catch (InvalidRating e) {
                System.out.println("Invalid Rating! Please try again.");
            }
        }
        scanner.nextLine();
    }

    // EFFECTS: shows the entire music library
    private void viewSongs() {
        if (library.getSongCount() == 0) {
            System.out.println("Youre music library is empty. Please add a song first.");
        } else {
            System.out.println("You currently have " + library.getSongCount() + " songs:\n");
            for (Song song: library.getSortedSongs()) {
                displaySong(song);
            }
        }
    }
        
    /*
     * EFFECTS: searches for a song in the music library
     *          if the song is found
     *              displays the song
     *              displays options to change the song's ratings
     *          if the song is not found
     *              displays song not found message
    */ 
    private void searchForSong() {
        System.out.println("Please enter the song title you would like to find:");
        String search = scanner.nextLine().toLowerCase();
        Song song = library.findSong(search);

        if (song != null) {
            System.out.println("Sond found!");
            displaySong(song);
            songMenu(song);
            String input = "";
            while (!input.equals("q")) {
                input = this.scanner.next();
                processSongCommand(input, song);
            }
        } else {
            System.out.println("Song not found.");
        }
    }

    // EFFECTS: displays a song menu for a song that has been searched and found
    private void displaySong(Song song) {
        System.out.println(song.getTitle() + ", " 
                            + song.getArtist() + ", " 
                            + song.getGenre() + ", " 
                            + "Lyrics Score: " + song.getLyricsRating() + ", "
                            + "Production Score: " + song.getProdRating() + ", "
                            + "Vocals Score: " + song.getVocalsRating() + ", "
                            + "Overall Score: " + song.calculateTotalScore() + ", "
                            + "Favourite? " + String.valueOf(song.getIsFavourite()));
    }

    // EFFECTS: gives users the option to change a song's ratings
    private void songMenu(Song song) {
        System.out.println("Please select an option:\n");
        System.out.println("\tcl -> change lyrics rating");
        System.out.println("\tcp -> change productions rating");
        System.out.println("\tcv -> change vocals rating");
        System.out.println("\tf -> change favourite status");
        System.out.println("\tq -> return to main menu");
    }

    // MODIFIES: this
    // EFFECTS: processes user command for one song
    private void processSongCommand(String input, Song song) {
        switch (input) {
            case "cl":
                setRating(song, "lyrics");
                songMenu(song);
                break;
            case "cp":
                setRating(song, "production");
                songMenu(song);
                break;
            case "cv":
                setRating(song, "vocals");
                songMenu(song);
                break;
            case "f":
                song.setIsFavourite(!song.getIsFavourite());
                System.out.println("Favourite Status updated.");
                songMenu(song);
                break;
            case "q":
                System.out.println("Returning to main menu...\n");
                break;
            default:
                System.out.println("Invalid option! Please try again.");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: prints a closing message and marks the program as not running
    private void quitApp() {
        System.out.println("Thanks for using RateMySong!");
        System.out.println("Have a good day!");
        this.isRunning = false;
    }
}
