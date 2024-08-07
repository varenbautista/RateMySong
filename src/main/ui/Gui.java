package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import exceptions.InvalidRating;
import model.EventLog;
import model.Event;
import model.Song;
import model.SongLibrary;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// represents a graphical user interface
public class Gui extends JFrame {
    private static final String JSON_STORE = "./data/log.json";
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;

    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    private SongLibrary library;
    private JTable libraryDisplay;
    private ImageIcon addSongImage;

    // EFFECTS: creates the main application window
    public Gui() {
        super("RateMySong");
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeModel();
        initializeButtons();
        initializeTable();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        loadPrompt();
        savePrompt();
    }

    // MODIFIES: this
    // EFFECTS: initializes main models (song and song library) and data
    private void initializeModel() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        addSongImage = new ImageIcon("./data/addSongImage.jpg");

        library = new SongLibrary();
    }

    // MODIFIES: this
    // EFFECTS: initializes buttons
    private void initializeButtons() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());

        JButton newSong = new JButton("Add");
        newSong.setActionCommand("Add");
        newSong.addActionListener(new ButtonListener());

        JButton removeSong = new JButton("Remove");
        removeSong.setActionCommand("Remove");
        removeSong.addActionListener(new ButtonListener());

        JButton searchSong = new JButton("Search");
        searchSong.setActionCommand("Search");
        searchSong.addActionListener(new ButtonListener());

        buttonPane.add(newSong);
        buttonPane.add(removeSong);
        buttonPane.add(searchSong);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: initializes the table to display the song library
    private void initializeTable() {
        libraryDisplay = new JTable();
        libraryDisplay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(libraryDisplay);
        add(scroll, BorderLayout.CENTER);
        updateTable();
    }

    // MODIFIES: this
    // EFFECTS: updates the table to display the current song library in descending order of total overall score
    private void updateTable() {
        String[] columns = {"Title", "Artist", "Genre", 
                            "Total Score", "Lyrics Rating", 
                            "Production Rating", "Vocals Rating", "Favourite"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        List<Song> songs = library.getSortedSongs();
        for (Song song : songs) {
            Object[] row = {
                song.getTitle(),
                song.getArtist(),
                song.getGenre(),
                song.calculateTotalScore(),
                song.getLyricsRating(),
                song.getProdRating(),
                song.getVocalsRating(),
                song.getIsFavourite(),
            };
            model.addRow(row);
        }
        libraryDisplay.setModel(model);
    }

    // MODIFIES: this
    // EFFECTS: initializes load song library prompt on start
    private void loadPrompt() {
        int loadOption = JOptionPane.showConfirmDialog(null, 
                                                "Would you like to load your last music library?", 
                                                "Load File", JOptionPane.YES_NO_OPTION);
        if (loadOption == JOptionPane.YES_OPTION) {
            try {
                library = jsonReader.read();
                updateTable();
            } catch (IOException e) {
                System.out.println("File not found from " + JSON_STORE);
            }
        }
    }

    // EFFECTS: initializes save song library prompt when exiting the application
    //          prints event log upon exiting application
    private void savePrompt() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                int saveOption = JOptionPane.showConfirmDialog(null, 
                                                                "Would you like to save your music library?", 
                                                                "Save File", JOptionPane.YES_NO_OPTION);
                if (saveOption == JOptionPane.YES_OPTION) {
                    try {
                        jsonWriter.open();
                        jsonWriter.write(library);
                        jsonWriter.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Unable to write file to " + JSON_STORE);
                    }
                    dispose();
                }
                printLog();
            }
        });
    }

    private void printLog() {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event.toString());
        }
    }

    // creates an Action Listener for buttons
    class ButtonListener implements ActionListener {

        // EFFECTS: processes button clicks
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Add":
                    addSong();
                    break;
                case "Remove":
                    removeSong();
                    break;
                case "Search":
                    searchSong();
                    break;
            }
        }

        // MODIFIES: this
        // EFFECTS: adds a song to the song library
        private void addSong() {
            String title = getInput("Please enter the title:", "Add Song");
            if (title == null || title.trim().isEmpty()) {
                return;
            }

            String artist = getInput("Please enter the arist:", "Add Song");
            if (artist == null || artist.trim().isEmpty()) {
                return;
            }

            String genre = getInput("Please enter the genre:", "Add Song");
            if (genre == null || genre.trim().isEmpty()) {
                return;
            }
            
            Song song = new Song(title, artist, genre);
            int lyricsRating = getRatingInput("Please rate the lyrics from 1-5:");
            int prodRating = getRatingInput("Please rate the production from 1-5:");
            int vocalsRating = getRatingInput("Please rate the vocals from 1-5:");
            boolean isFavourite = markFavourite();

            tryAddSong(song, lyricsRating, prodRating, vocalsRating, isFavourite);
            library.addSong(song);
            updateTable();
            successMessage("Song added successfully!");
        }

        // EFFECTS: displays a success message
        private void successMessage(String message) {
            JOptionPane.showMessageDialog(Gui.this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        // EFFECTS: displays an error message
        private void errorMessage(String message) {
            JOptionPane.showMessageDialog(Gui.this, message, "Error", JOptionPane.ERROR_MESSAGE);
        }

        // EFFECTS: sets a favourite status when adding a song
        private boolean markFavourite() {
            int option = JOptionPane.showConfirmDialog(Gui.this, 
                                            "Do you want to mark this song as a favourite?", 
                                            "Set Favourite", JOptionPane.YES_NO_OPTION);
            return option == JOptionPane.YES_NO_OPTION;
        }

        // EFFECTS: gets input from the user
        private String getInput(String message, String title) {
            return (String) JOptionPane.showInputDialog(null, message, title, 
                                                        JOptionPane.QUESTION_MESSAGE, addSongImage, null, "");
        }

        // MODIFIES: this
        // EFFECTS: tries to add song ratings
        //          catches InvalidRating if the given rating is invalid
        private void tryAddSong(Song song, int lyricsRating, int prodRating, int vocalsRating, boolean isFavourite) {
            try {
                song.setLyricsRating(lyricsRating);
                song.setProdRating(prodRating);
                song.setVocalsRating(vocalsRating);
                song.setIsFavourite(isFavourite);
            } catch (InvalidRating e) {
                errorMessage("Invalid Rating. Please try again.");
            }
        }

        // MODIFIES: this
        // EFFECTS: creates a popup to get a rating input for a song
        private int getRatingInput(String message) {
            boolean isValidRating = false;
            int rating = 1;
            while (!isValidRating) {
                String input = getInput(message, "Add Song");
                rating = Integer.parseInt(input.trim());

                if (input == null || input.trim().isEmpty()) {
                    return 1;
                }
                if (rating >= 1 && rating <= 5) {
                    isValidRating = true;
                } else {
                    errorMessage("Invalid Rating. Please try again.");
                }
            }
            return rating;
        }

        // MODIFIES: this
        // EFFECTS: removes a selected song from the library
        //          displays error message if song is not found
        //          otherwise, removes the song and updates table
        private void removeSong() {
            int selectedSong = libraryDisplay.getSelectedRow();
            if (selectedSong == -1) {
                errorMessage("Please select a song");
            }

            String title = (String) libraryDisplay.getValueAt(selectedSong, 0);
            int confirmation = JOptionPane.showConfirmDialog(Gui.this, "Are you sure you want to remove " 
                                                            + title + "?", "Confirm Remove", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                Song song = library.findSong(title);
                if (song == null) {
                    errorMessage("Song not found.");
                } else {
                    library.removeSong(song);
                    updateTable();
                    successMessage("Song removed!");
                }
            }
        }

        // EFFECTS: searches for a song in the song library
        //          displays song if found
        //          otherwise displays error message
        private void searchSong() {
            String title = getInput("Please enter the song title to be searched:", "Search Song");
            if (title == null || title.trim().isEmpty()) {
                return;
            }

            Song song = library.findSong(title);
            if (song == null) {
                errorMessage("Song not found.");
            } else {
                displaySong(song);
            }
        }

        // EFFECTS: displays a song in a popup
        //          gives the user the option to change details about the song
        //          tries to update the song based on user changes
        private void displaySong(Song song) {
            JPanel songPanel = new JPanel(new GridLayout(0, 1));
            songPanel.add(new JLabel("Title: " + song.getTitle()));
            songPanel.add(new JLabel("Artist: " + song.getArtist()));
            songPanel.add(new JLabel("Genre: " + song.getGenre()));
            songPanel.add(new JLabel("Title: " + song.calculateTotalScore()));

            JTextField lyricsRatingField = new JTextField(String.valueOf(song.getLyricsRating()));
            songPanel.add(new JLabel("Lyrics Rating:"));
            songPanel.add(lyricsRatingField);

            JTextField prodRatingField = new JTextField(String.valueOf(song.getProdRating()));
            songPanel.add(new JLabel("Production Rating:"));
            songPanel.add(prodRatingField);

            JTextField vocalsRatingField = new JTextField(String.valueOf(song.getVocalsRating()));
            songPanel.add(new JLabel("Vocals Rating:"));
            songPanel.add(vocalsRatingField);

            JCheckBox favouriteCheckBox = new JCheckBox("Favourite", song.getIsFavourite());
            songPanel.add(favouriteCheckBox);


            int result = JOptionPane.showConfirmDialog(Gui.this, songPanel, "Update Song", 
                                                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (result == JOptionPane.OK_OPTION) {
                tryUpdateSong(song, lyricsRatingField, prodRatingField, vocalsRatingField, favouriteCheckBox);
            }
        }

        // MODIFIES: song
        // EFFECTS: tries to update song based on user input
        //          displays error message if rating is invalid
        private void tryUpdateSong(Song song, JTextField lyricsRatingField, JTextField prodRatingField,
                JTextField vocalsRatingField, JCheckBox favouriteCheckBox) {
            try {
                int lyricsRating = Integer.parseInt(lyricsRatingField.getText().trim());
                int prodRating = Integer.parseInt(prodRatingField.getText().trim());
                int vocalsRating = Integer.parseInt(vocalsRatingField.getText().trim());
   
                song.setLyricsRating(lyricsRating);
                song.setProdRating(prodRating);
                song.setVocalsRating(vocalsRating);
                song.setIsFavourite(favouriteCheckBox.isSelected());
   
                updateTable();
                successMessage("Song Updated!");
            } catch (NumberFormatException | InvalidRating e) {
                errorMessage("Invlaid Rating. Please try again.");
            }
        }
    }
}
