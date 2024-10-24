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
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

import org.json.*;

// Represents a reader that reads a song library from JSON data stored in file
// All code in this class is modelled on the JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;

    }

    // EFFECTS: reads a song library from file and returns it;
    // throws IOException if an error occurs reading data from file
    public SongLibrary read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSongLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses SongLibrary from JSON object and returns it
    private SongLibrary parseSongLibrary(JSONObject jsonObject) {
        SongLibrary sl = new SongLibrary();
        addSongs(sl, jsonObject);
        return sl;
    }

    // MODIFIES: sl
    // EFFECTS: parses Songs from JSON object and adds them to SongLibrary
    private void addSongs(SongLibrary sl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("songList");
        for (Object json : jsonArray) {
            JSONObject nextSong = (JSONObject) json;
            addSong(sl, nextSong);
        }
    }

    // MODIFIES: sl
    // EFFECTS: parses one Song from JSON object and adds it to SongLibrary
    private void addSong(SongLibrary sl, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String composer = jsonObject.getString("composer");
        String instrument = jsonObject.getString("instrument");
        int barCount = jsonObject.getInt("barCount");
        int tempo = jsonObject.getInt("tempo");
        List<Session> sessions = getSessionsFromJSON(jsonObject);
        List<Day> days = getDaysFromJSON(jsonObject);
        Song song = new Song(title, composer, instrument, barCount, tempo);
        for (Session s : sessions) {
            song.logSessionToSessionList(s);
        }
        for (Session s : sessions) {
            song.logSessionToDay(s);
        }
        sl.addSong(song);
    }

    public List<Session> getSessionsFromJSON(JSONObject jsonObject) {
        List<Session> sessions = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("sessions");
        for (Object json : jsonArray) {
            JSONObject nextSession = (JSONObject) json;
            int tempo = nextSession.getInt("tempo");
            int startBar = nextSession.getInt("startBar");
            int endBar = nextSession.getInt("endBar");
            float mastery = nextSession.getFloat("mastery");
            long duration = nextSession.getLong("duration");
            LocalDate date = LocalDate.parse(nextSession.getString("date"));
            LocalTime startTime = LocalTime.parse(nextSession.getString("startTime"));
            LocalTime endTime = LocalTime.parse(nextSession.getString("endTime"));
            Session newSession = new Session(tempo, startBar, endBar, mastery, duration, date, startTime, endTime);
            sessions.add(newSession);
        }
        return sessions;
    }

    public List<Day> getDaysFromJSON(JSONObject jsonObject) {
        List<Day> days = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("days");
        for (Object json : jsonArray) {

            JSONObject nextDay = (JSONObject) json;
            List<Session> sessions = getSessionsFromJSON(nextDay);
            LocalDate date = LocalDate.parse(nextDay.getString("date"));
            Day newDay = new Day(date);
            for (Session s : sessions) {
                newDay.addSession(s);
            }
            days.add(newDay);
        }

        return days;
    }
}
