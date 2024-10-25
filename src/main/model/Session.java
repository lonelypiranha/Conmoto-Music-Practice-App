package model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.json.JSONObject;

import persistence.Writable;

/*
 * 1. A representation of a practice session for a particular song
 * 2. A practice session consists of a tempo, starting bar number, ending bar number, 
 * overall mastery, duration, practice date, starting time, and ending time.
 */
public class Session implements Writable {
    private int tempo;
    private int startBar;
    private int endBar;
    private float mastery;
    private long duration;
    private LocalDate date;
    private LocalTime starTime;
    private LocalTime endTime;

    // REQUIRES:
    // 1. tempo, startBar, and endBar are integers and > 0
    // 2. endBar >= startBar
    // 3. mastery is a float and must be 0 <= mastery <= 10
    // EFFECT: Creates a practice session wih tempo, bars practiced, mastery,
    // duration, and practice date and time
    public Session(int tempo, int startBar, int endBar, float mastery, long duration, LocalDate date,
            LocalTime startTime, LocalTime endTime) {
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

    // This method is modelled on the JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("tempo", tempo);
        json.put("startBar", startBar);
        json.put("endBar", endBar);
        json.put("mastery", mastery);
        json.put("duration", duration);
        json.put("date", date);
        json.put("startTime", starTime);
        json.put("endTime", endTime);
        return json;
    }
}
