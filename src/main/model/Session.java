package model;
import java.time.LocalDate;
import java.time.LocalTime;

/*
 * A representation of a practice session for a particular song
 * A practice session consists of a tempo, starting bar number, ending bar number, overall mastery, duration, practice date, starting time, and ending time.
 */
public class Session {

    // REQUIRES: tempo, startBar, endBar, and duration must be > 0, mastery must be >= 0, endBar >= startBar
    // EFFECT: Creates a practice session wih tempo, bars practiced, mastery, duration, and practice date and time
    public Session(int tempo, int startBar, int endBar, float mastery, long duration, LocalDate date, LocalTime startTime, LocalTime endTime) {
        
    }
    
    public int getTempo() {
        return 0;
    }

    public void setTempo(String tempo) {

    }

    public int getStartBar() {
        return 0;
    }

    public void setStartBar(int startBar) {

    }

    public int getEndBar() {
        return 0;
    }

    public void setEndBar(int endBar) {

    }

    public float getOverallMastery() {
        return 0.0f;
    }

    public void setOverallMastery(int overallMastery) {

    }

    public long getDuration() {
        return 0L;
    }

    public void setDuration(long duration) {

    }

    public LocalDate getPracticeDate() {
        return null;
    }

    public void setPracticeDate(LocalDate practiceDate) {

    }

    public LocalTime getStartTime() {
        return null;
    }

    public void setStartTime(LocalTime startTime) {

    }

    public LocalTime getEndTime() {
        return null;
    }

    public void setEndTime(LocalTime endTime) {

    }
}
