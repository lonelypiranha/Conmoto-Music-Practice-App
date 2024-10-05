package model;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;

/*
 * A representation of a song that the user has added to the song library.
 * Each song contains a title, composer, instrument, number of bars, and tempo.
 * Each song will have a list of sessions which contains all the practice sessions fro the particular song.
 */
public class Song {

    // EFFECTS: constructs a song with title, composer, instrument, bar count, and tempo
    public Song(String title, String composer, String instrument, int barCount, int tempo) {
        
    }

    // MODIFIES: this
    // EFFECTS: Logs a recently ended practice session into a list of practice sessions for this song
    public void logSession(long duration, String tempo, int startBar, int endBar, int overallMastery, LocalDate practiceDate, LocalTime startTime, LocalTime endTime) {
        
    }

    // EFFECTS: returns a list of practice sessions completed in the given month of a year
    public List<Session> monthlyProgressSummary(Month m, Year y) {
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

    
}
