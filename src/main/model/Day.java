package model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/*
 * A representation of a day in a year.
 * Each day contains a list of practice sessions that are started on that day
 * The day class contains methods for finding the total practice duration and average values of practice tempos and overall masteries
 * of all practice session started on that day. 
 * This is useful for displaying information for monthly progress summaries.
 */
public class Day {
    private List<Session> sessionList;
    private LocalDate date;

    // EFFECT: Creates a day with certain date and an empty list of sessions
    public Day(LocalDate date) {
        sessionList = new ArrayList<>();
        this.date = date;
    }
    
    // EFFECT: Calculates the average practice duration of a specific song in a particular day
    public long getTotalDuration() {
        long totalDuration = 0L;
        for (Session s : sessionList) {
            totalDuration += s.getDuration();
        }
        return totalDuration;
    }

    // REQUIRES: this must have a non-empty list of sessions
    // EFFECT: Calculates the average practice tempo of a specific song in a particular day
    public int getAverageTempo() {
        int totalTempo = 0;
        for (Session s : sessionList) {
            totalTempo += s.getTempo();
        }
        int averageTempo = totalTempo/sessionList.size();
        return averageTempo;
    }

    // REQUIRES: this must have a non-empty list of sessions
    // EFFECT: Calculates the average mastery level of a specific song in a particular day 
    public float getAverageMastery() {
        float totalMastery = 0;
        for (Session s : sessionList) {
            totalMastery += s.getOverallMastery();
        }
        float averageMastery = totalMastery/sessionList.size();
        String averageMasteryString = String.valueOf(averageMastery);
        String averageMasteryStringTruncated = averageMasteryString.substring(0, 3);
        float finalAverageMastery = Float.parseFloat(averageMasteryStringTruncated);
        return finalAverageMastery;
    }

    // REQUIRES: Session added must have identical date as the date of the Day object
    // MODIFIES: this
    // EFFECT: Adds a session into the list of sessions for the particular day
    public void addSession(Session s) {
        sessionList.add(s);
    }

    public List<Session> getSessionList() {
        return sessionList;
    }
    public LocalDate getLocalDate() {
        return date;
    }



    


}
