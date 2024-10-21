package persistence;

import model.Day;
import model.Session;
import model.Song;
import model.SongLibrary;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads a song library from JSON data stored in file
// All code in this class is modelled on the JsonSerializationDemo
public class JsonReader {
    

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        
    }

    // EFFECTS: reads a song library from file and returns it;
    // throws IOException if an error occurs reading data from file
    public SongLibrary read() throws IOException {
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return null;
    }

    // EFFECTS: parses song library from JSON object and returns it
    private SongLibrary parseSongLibrary(JSONObject jsonObject) {
        return null;  
    }

    // MODIFIES: sl
    // EFFECTS: parses songs from JSON object and adds them to song library
    private void addSongs(SongLibrary sl, JSONObject jsonObject) {
        
        }
    

    // MODIFIES: sl
    // EFFECTS: parses one song from JSON object and adds it to song library
    private void addSong(SongLibrary sl, JSONObject jsonObject) {
        
    }
}

