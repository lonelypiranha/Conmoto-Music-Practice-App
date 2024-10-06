package model;

import java.util.List;

/*
 * A representation of a collection of songs that the user has added to the song library
 * The song library can be filtered by composer or by instrument.
 */

public class SongLibrary {

    // EFFECTS: constructs a song library with no songs inside
     public SongLibrary() {

     }

     // MODIFIES: this
     // EFFECTS: adds a song to the song library
     public void addSong(Song s) {

     }

     // EFFECTS: filters through the song library and creates a list of songs composed for the given instrument
     public List<Song> filterByInstrument(String instrument) {
        return null;

     }

     // EFFECTS: filters through the song library and creates a list of songs composed by the given composer
     public List<Song> filterByComposer(String composer) {
        return null;

     }

     public List<Song> getSongList() {
        return null;
    }

    public void setSongList(List<Song> songList) {

    }

    
}
