package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

import java.time.LocalDate;
import java.time.Month;

/*
 * 1. A representation of a song
 * 2. Each song contains a title, composer, instrument, number of bars, and tempo.
 * 3. Each song will have a list of sessions which contains all the practice sessions for the particular song.
 * 4. Each song will also have a list of days containiing the days in which the song was practiced.
 * 5. The Song class contains methods for adding a practice session to the full session list,
 * logging a practice session into its corresponding day in the day list, and also returning
 * a list of days that are in a given month of the year.
 */
public class Song implements Writable{
    private String title;
    private String composer;
    private String instrument;
    private int barCount;
    private int tempo;
    private List<Session> sessions;
    private List<Day> days;

    // REQUIRES: barCount and tempo must be integers and > 0
    // EFFECTS: constructs a song with title, composer, instrument, bar count, and
    // tempo, and also with an empty Sessions and Days list.
    public Song(String title, String composer, String instrument, int barCount, int tempo) {
        this.title = title;
        this.composer = composer;
        this.instrument = instrument;
        this.barCount = barCount;
        this.tempo = tempo;
        sessions = new ArrayList<>();
        days = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Logs a recently ended practice session into a list of ALL practice
    // sessions for this song
    public void logSessionToSessionList(Session s) {
        sessions.add(s);
    }

    // MODIFIES: this
    // EFFECTS:
    // 1. Logs a recently ended practice session into the Day with the same date as
    // the date of the recently ended session.
    // 2. If it is the first time that this method is called for a particular song
    // in a particular day, create a new Day and log the recently ended session
    // into it
    public void logSessionToDay(Session s) {
        List<LocalDate> allDates = new ArrayList<>();
        for (Day d : days) {
            allDates.add(d.getLocalDate());
        }
        boolean checker = true;
        for (int i = 0; i < allDates.size(); i++) {
            if (allDates.get(i).equals(s.getPracticeDate())) {
                checker = false;
                days.get(i).addSession(s);
                break;
            }
        }
        if (checker) {
            Day newDay = new Day(s.getPracticeDate());
            newDay.addSession(s);
            days.add(newDay);
        }
    }

    // EFFECTS: returns a list of Days that are in a given month of the year
    public List<Day> returnDaysInMonth(Month month, int year) {
        List<Day> dayList = new ArrayList<>();
        for (Day d : days) {
            if (d.getLocalDate().getMonth().equals(month) && (d.getLocalDate().getYear() == year)) {
                dayList.add(d);
            }
        }
        return dayList;
    }

    public String getTitle() {
        return title;
    }

    public String getComposer() {
        return composer;
    }

    public String getInstrument() {
        return instrument;
    }

    public int getBarNumber() {
        return barCount;
    }

    public int getTargetTempo() {
        return tempo;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public List<Day> getDays() {
        return days;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("composer", composer);
        json.put("instrument", instrument);
        json.put("barCount", barCount);
        json.put("tempo", tempo);
        json.put("sessions", sessionsToJson());
        json.put("days", daysToJson());
        return json;
    }

    // EFFECTS: returns sessions in this Song as a JSON array
    private JSONArray sessionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Session s : sessions) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

    private JSONArray daysToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Day d : days) {
            jsonArray.put(d.toJson());
        }

        return jsonArray;
    }
}
