package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

import java.time.LocalDate;

/*
 * 1. A representation of a day in a year.
 * 2. Each Day contains a list of practice sessions that are started on that day.
 * 3. The Day class contains methods for finding the total practice duration 
 * and average values of practice tempos and masteries of all practice sessions
 * in the session list. This is useful for displaying information for monthly progress summaries.
 */
public class Day implements Writable{
    private List<Session> sessionList;
    private LocalDate date;

    // EFFECT: Creates a day with certain date and an empty list of sessions
    public Day(LocalDate date) {
        sessionList = new ArrayList<>();
        this.date = date;
    }

    // EFFECT: Calculates the total practice duration of all practice sessions in
    // the sessionList
    public long getTotalDuration() {
        long totalDuration = 0L;
        for (Session s : sessionList) {
            totalDuration += s.getDuration();
        }
        return totalDuration;
    }

    // REQUIRES: this must have a non-empty sessionList
    // EFFECT: Calculates the average practice tempo of all practice sessions in the
    // sessionList
    public int getAverageTempo() {
        int totalTempo = 0;
        for (Session s : sessionList) {
            totalTempo += s.getTempo();
        }
        int averageTempo = totalTempo / sessionList.size();
        return averageTempo;
    }

    // REQUIRES: this must have a non-empty sessionList
    // EFFECT: Calculates the average mastery level of all practice sessions in the
    // sessionList
    public float getAverageMastery() {
        float totalMastery = 0;
        for (Session s : sessionList) {
            totalMastery += s.getOverallMastery();
        }
        float averageMastery = totalMastery / sessionList.size();
        String averageMasteryString = String.valueOf(averageMastery);
        String averageMasteryStringTruncated = averageMasteryString.substring(0, 3);
        float finalAverageMastery = Float.parseFloat(averageMasteryStringTruncated);
        return finalAverageMastery;
    }

    // REQUIRES: Session added must have identical date as this.getLocalDate()
    // MODIFIES: this
    // EFFECT: Adds a session into the sessionList
    public void addSession(Session s) {
        sessionList.add(s);
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

    public LocalDate getLocalDate() {
        return date;
    }

    // This method is modelled on the JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("sessions", sessionsToJson());
        json.put("date", date);

        return json;
    }

    // EFFECTS: returns sessions in this Day as a JSON array
    // This method is modelled on the JsonSerializationDemo
    private JSONArray sessionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Session s : sessionList) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

}
