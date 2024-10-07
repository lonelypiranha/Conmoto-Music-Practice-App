package model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.Month;

/*
 * A representation of a song that the user has added to the song library.
 * Each song contains a title, composer, instrument, number of bars, and tempo.
 * Each song will have a list of sessions which contains all the practice sessions fro the particular song.
 * Each song will also have a list of days containiing all practice sessions started on that day
 */
public class Song {
    private String title;
    private String composer;
    private String instrument;
    private int barCount;
    private int tempo;
    private List<Session> sessions;
    private List<Day> days;

    // REQUIRES: barCount and tempo must be > 0
    // EFFECTS: constructs a song with title, composer, instrument, bar count, and
    // tempo
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
    // 1. Logs a recently ended practice session into the day in which the practice
    // session is started
    // 2. If this is the first that this method is called in a particular day,
    // create a new day and log the current practice session into it
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
    public List<Day> monthlyProgressSummary(Month month, int year) {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public int getBarNumber() {
        return barCount;
    }

    public void setBarNumber(int barCount) {
        this.barCount = barCount;
    }

    public int getTargetTempo() {
        return tempo;
    }

    public void setTargetTempo(int tempo) {
        this.tempo = tempo;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public List<Day> getDays() {
        return days;
    }
}
