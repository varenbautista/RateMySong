# My Personal Project: Rate My Song

## What Will the Application Do?
For my final project of CPSC 210 Software Construction, I will be making a **Song Rating Application**
based on the songs inputted by the user. Users will be able to input songs, rate the lyricism, production,
and vocals on a scale of 1 to 5, and add notes as to what they did and did not like about the song. 
The user will then be able to view their list of songs from most liked to least liked based on each song's
overall score.

## Who Will Use It?
This application is targetted towards users want to take their music listening a step deeper. It's one thing
to listen to a song and simply "like it." Dissecting a song's lyrics, sound degisn, and vocal performance
is a completely different process. This application will allow users to record their thoughts about certain
songs, appreciate music more, and keep them in a well organized list.

## Why is this Project of Interest to Me?
This application intrigued me because of my particular love for music. I believe music is one of the 
most diverse and creative expressions of emotions. This application will encourage users to dig deeper
than what is presented in a three minute expression of the artist's character. 

## User Stories
- As a user, I want to be able to create a song and rate its lyricism, production, and vocals from 1-5.
- As a user, I want to be able to remove a song from the music library.
- As a user, I want to be able to list all inputted songs from the greatest to least overall score.
- As a user, I want to be able to search for an already inputted song and change its existing rating 
for lyricism, production, and vocals. 
- As a user, I want to be able to favourite a song and have its status listed in the music library.
- As a user, I want to be able to save my music library and ratings (if I so choose).
- As a user, I want to be able to load my music library and ratings (if I so choose).

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by
pressing the "Add" button to start a sequence of popups to add a song to your music library.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by
pressing the "Search" button to search for a song in your music library and update its ratings and favourite
status. 
- You can remove a song by selecting a song and pressing the "Remove" button.
- You can locate my visual component by adding a song. The title, artist, and genre popups displays an image
to represent adding a song. (./data/addSongImage.jpg).
- You can save the state of my application by clicking "Yes" or "No" before exiting the application.
- You can reload the state of my application by clicking "Yes" or "No" when opening the application.

# Phase 4: Task 2
Wed Aug 07 13:50:00 PDT 2024
Music library has been sorted
Wed Aug 07 13:50:02 PDT 2024
Music Library loaded

Wed Aug 07 13:50:02 PDT 2024
Lyrics rating for Happiness is a Butterfly updated to 4
Wed Aug 07 13:50:02 PDT 2024
Production rating for Happiness is a Butterfly updated to 5
Wed Aug 07 13:50:02 PDT 2024
Vocals rating for Happiness is a Butterfly updated to 5
Wed Aug 07 13:50:02 PDT 2024
Happiness is a Butterfly favourite status updated to true
Wed Aug 07 13:50:02 PDT 2024
Happiness is a Butterfly added to music library

Wed Aug 07 13:50:02 PDT 2024
Lyrics rating for Back to December updated to 5
Wed Aug 07 13:50:02 PDT 2024
Production rating for Back to December updated to 3
Wed Aug 07 13:50:02 PDT 2024
Vocals rating for Back to December updated to 5
Wed Aug 07 13:50:02 PDT 2024
Back to December favourite status updated to true
Wed Aug 07 13:50:02 PDT 2024
Back to December added to music library

Wed Aug 07 13:50:02 PDT 2024
Lyrics rating for Vroom Vroom updated to 5
Wed Aug 07 13:50:02 PDT 2024
Production rating for Vroom Vroom updated to 5
Wed Aug 07 13:50:02 PDT 2024
Vocals rating for Vroom Vroom updated to 5
Wed Aug 07 13:50:02 PDT 2024
Vroom Vroom favourite status updated to true
Wed Aug 07 13:50:02 PDT 2024
Vroom Vroom added to music library

Wed Aug 07 13:50:02 PDT 2024
Music library has been sorted
Wed Aug 07 13:50:04 PDT 2024
Music Library saved

# Phase 4: Task 3
- If I had more time, I would refactor my code to make it more than a song rater, but a product rater in general
(songs, audiobooks, podcasts, etc). I would implement an abstract class called Product and List of Product, and 
have Song, Audiobook, Podcast, and other products extend the Product class, and SongLibrary, AudiobookLibrary, and
PodcastLibrary. These libraries/ playlist would then display in the GUI. The abstract classes would make it easier
to implement additional types of products for the user to rate. 
