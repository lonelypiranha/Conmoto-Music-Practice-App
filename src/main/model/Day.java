package model;

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

    // EFFECT: Creates a day with certain date and an empty list of sessions
    public Day(LocalDate date) {

    }
    
    // EFFECT: Calculates the average practice duration of a specific song in a particular day
    public long getTotalDuration() {
        return 0L;
    }

    // REQUIRES: this must have a non-empty list of sessions
    // EFFECT: Calculates the average practice tempo of a specific song in a particular day
    public int getAverageTempo() {
        return 0;
    }

    // REQUIRES: this must have a non-empty list of sessions
    // EFFECT: Calculates the average mastery level of a specific song in a particular day 
    public float getAverageMastery() {
        return 0.0f;
    }

    // REQUIRES: Session added must have identical date as the date of the Day object
    // MODIFIES: this
    // EFFECT: Adds a session into the list of sessions for the particular day
    public void addSession(Session s) {

    }

    public List<Session> getSessionList() {
        return null;
    }

    public void setSessionList(List<Session> sl) {

    }

    public LocalDate getLocalDate() {
        return null;
    }

    public void setLocalDate(LocalDate d1) {

    }



    


}
