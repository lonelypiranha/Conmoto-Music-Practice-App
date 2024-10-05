package model;
import java.time.LocalDate;
import java.time.LocalTime;

/*
 * A representation of a practice session for a particular song
 * A practice session consists of a tempo, starting bar number, ending bar number, overall mastery, duration, practice date, starting time, and ending time.
 */
public class Session {

    // EFFECT: Creates a practice session wih tempo, bars practiced, mastery, duration, and practice date and time
    public Session() {

    }
    
    public String getTempo() {
        return "";
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
