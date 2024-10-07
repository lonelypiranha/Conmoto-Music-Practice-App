package model;
import java.time.LocalDate;
import java.time.LocalTime;

/*
 * A representation of a practice session for a particular song
 * A practice session consists of a tempo, starting bar number, ending bar number, overall mastery, duration, practice date, starting time, and ending time.
 */
public class Session {
    private int tempo;
    private int startBar;
    private int endBar;
    private float mastery;
    private long duration;
    private LocalDate date;
    private LocalTime starTime;
    private LocalTime endTime;

    // REQUIRES: tempo, startBar, endBar, and duration must be > 0, mastery must be >= 0, endBar >= startBar
    // EFFECT: Creates a practice session wih tempo, bars practiced, mastery, duration, and practice date and time
    public Session(int tempo, int startBar, int endBar, float mastery, long duration, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.tempo = tempo;
        this.startBar = startBar;
        this.endBar = endBar;
        this.mastery = mastery;
        this.duration = duration;
        this.date = date;
        this.starTime = startTime;
        this.endTime = endTime;
    }
    
    public int getTempo() {
        return tempo;
    }
    public int getStartBar() {
        return startBar;
    }
    public int getEndBar() {
        return endBar;
    }
    public float getOverallMastery() {
        return mastery;
    }
    public long getDuration() {
        return duration;
    }
    public LocalDate getPracticeDate() {
        return date;
    }
    public LocalTime getStartTime() {
        return starTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
}
