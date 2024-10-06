package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DayTest {

    Day day1;
    Session ses1;
    Session ses2;
    Session ses3;
    Session ses4;
    Session ses5;

    @BeforeEach
    void runBefore() {
        day1 = new Day(LocalDate.of(2011, 8, 3));
        ses1 = new Session(50, 1, 50, 3.5f, 1756, LocalDate.of(2011, 8, 3), LocalTime.of(22, 21, 21), LocalTime.of(22, 50, 37));
        ses2 = new Session(100, 37, 44, 4.0f, 119, LocalDate.of(2011, 8, 3), LocalTime.of(11, 9, 11), LocalTime.of(11, 11, 10));
        ses3 = new Session(70, 12, 22, 2.2f, 2, LocalDate.of(2011, 8, 3), LocalTime.of(1, 9, 22), LocalTime.of(1, 9, 24));
        ses4 = new Session(50, 2, 50, 3.5f, 1756, LocalDate.of(2011, 8, 3), LocalTime.of(22, 21, 21), LocalTime.of(22, 50, 37));
        ses5 = new Session(103, 37, 44, 4.0f, 119, LocalDate.of(2011, 8, 3), LocalTime.of(11, 9, 11), LocalTime.of(11, 11, 10));
    }

    @Test
    void testConstructor() {
        assertEquals(day1.getLocalDate(), LocalDate.of(2011, 8, 3));
        assertEquals(day1.getSessionList().size(), 0);
    }

    @Test
    void testAddSession() {
        assertEquals(day1.getSessionList().size(), 0);
        day1.addSession(ses1);
        assertEquals(day1.getSessionList().size(), 1);
        day1.addSession(ses5);
        assertEquals(day1.getSessionList().size(), 2);
    }

    @Test
    void testGetTotalDuration() {
        assertEquals(day1.getTotalDuration(), 0);
        day1.addSession(ses1);
        assertEquals(day1.getTotalDuration(), 1756);
        day1.addSession(ses2);
        day1.addSession(ses3);
        day1.addSession(ses4);
        day1.addSession(ses5);
        assertEquals(day1.getTotalDuration(), 3752);
    }

    @Test
    void testGetAverageTempo() {
        day1.addSession(ses1);
        assertEquals(day1.getAverageTempo(), 50);
        day1.addSession(ses2);
        day1.addSession(ses3);
        day1.addSession(ses4);
        day1.addSession(ses5);
        assertEquals(day1.getAverageTempo(), 74);
    }

    @Test
    void testGetAverageMastery() {
        day1.addSession(ses1);
        assertEquals(day1.getAverageMastery(), 3.5f);
        day1.addSession(ses2);
        day1.addSession(ses3);
        day1.addSession(ses4);
        day1.addSession(ses5);
        assertEquals(day1.getAverageMastery(), 3.4f);
    }
    
}
