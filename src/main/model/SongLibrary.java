package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

/*
 * 1. A representation of a collection of songs that the user has added to the song library.
 * 2. The song library contains methods for adding and removing songs to/from the song library.
 * 3. The song library can be filtered by composer or by instrument.
 */

public class SongLibrary implements Writable {
    private List<Song> songList;

    // EFFECTS: constructs an empty song library with no songs inside
    public SongLibrary() {
        songList = new ArrayList<>();
    }

    // REQUIRES: Song to be added must have a different title to the songs already
    // in the library
    // MODIFIES: this
    // EFFECTS: adds a song to the song library
    public void addSong(Song s) {
        songList.add(s);
        EventLog.getInstance().logEvent(new Event("A song with the title " + s.getTitle() + " has been added."));
    }

    // MODIFIES: this
    // EFFECTS: removes a song from the song library, returns true if successful,
    // return false otherwise
    public boolean removeSong(Song s) {
        if (songList.contains(s)) {
            songList.remove(s);
            EventLog.getInstance().logEvent(new Event("A song with the title " + s.getTitle() + " was removed."));
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: filters through the song library and creates a list of songs
    // composed for the given instrument
    public List<Song> filterByInstrument(String instrument) {
        List<Song> songFilteredInstrument = new ArrayList<>();
        for (Song s : songList) {
            if (s.getInstrument().equals(instrument)) {
                songFilteredInstrument.add(s);
            }
        }
        EventLog.getInstance().logEvent(new Event("Song library is filtered by the instrument " + instrument));
        return songFilteredInstrument;

    }

    // EFFECTS: filters through the song library and creates a list of songs
    // composed by the given composer
    public List<Song> filterByComposer(String composer) {
        List<Song> songFilteredComposer = new ArrayList<>();
        for (Song s : songList) {
            if (s.getComposer().equals(composer)) {
                songFilteredComposer.add(s);
            }
        }
        EventLog.getInstance().logEvent(new Event("Song library is filtered by the composer " + composer));
        return songFilteredComposer;

    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    // This method is modelled on the JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("songList", songsToJson());
        return json;
    }

    // EFFECTS: returns songs in this SongLibrary as a JSON array
    // This method is modelled on the JsonSerializationDemo
    private JSONArray songsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song s : songList) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

}
