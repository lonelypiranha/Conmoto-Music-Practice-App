package model;
import java.util.List;
import java.time.Month;

/*
 * A representation of a song that the user has added to the song library.
 * Each song contains a title, composer, instrument, number of bars, and tempo.
 * Each song will have a list of sessions which contains all the practice sessions fro the particular song.
 * Each song will also have a list of days containiing all practice sessions started on that day
 */
public class Song {

    // REQUIRES: barCount and tempo must be > 0
    // EFFECTS: constructs a song with title, composer, instrument, bar count, and tempo
    public Song(String title, String composer, String instrument, int barCount, int tempo) {
        
    }

    // MODIFIES: this
    // EFFECTS: Logs a recently ended practice session into a list of ALL practice sessions for this song
    public void logSessionToSessionList(Session s) {
        
    }
    // MODIFIES: this
    // EFFECTS: 
    //1. Logs a recently ended practice session into the day in which the practice session is started
    //2. If this is the first that this method is called in a particular day, create a new day and log the current practice session into it
    public void logSessionToDay(Session s) {
        
    }

    // EFFECTS: returns a list of Days that are in a given month of the year
    public List<Day> monthlyProgressSummary(Month month, int year) {
        return null;
    }


    public String getTitle() {
        return "";
    }
    public void setTitle(String title) {

    }
    public String getComposer() {
        return "";
    }
    public void setComposer(String composer) {

    }
    public String getInstrument() {
        return "";
    }
    public void setInstrument(String instrument) {

    }
    public int getBarNumber() {
        return 0;
    }
    public void setBarNumber(int barCount) {

    }
    public int getTargetTempo() {
        return 0;
    }
    public void setTargetTempo(int tempo) {

    }
    public List<Session> getSessions() {
        return null;
    }
    public void setSessions(List<Session> sessions) {

    }
    public List<Day> getDays() {
        return null;
    }
    public void setDays(List<Day> days) {

    }

    
}
