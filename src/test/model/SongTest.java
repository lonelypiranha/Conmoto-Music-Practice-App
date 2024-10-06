package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SongTest {
    Song song1;
    Song song2;

    Session ses1;
    Session ses2;
    Session ses3;
    Session ses4;
    Session ses5;

    @BeforeEach
    void runBefore() {
        song1 = new Song("Intermezzo op.118 no. 2", "Brahms", "Piano", 117, 72);
        song2 = new Song("Serenade for strings", "Tchaikovsky", "Violin", 293, 110);

        ses1 = new Session(50, 2, 50, 3.5f, 1756, LocalDate.of(2024, 10, 5), LocalTime.of(22, 21, 21), LocalTime.of(22, 50, 37));
        ses2 = new Session(100, 37, 44, 4.0f, 119, LocalDate.of(1999, 10, 22), LocalTime.of(11, 9, 11), LocalTime.of(11, 11, 10));
        ses3 = new Session(70, 12, 22, 2.2f, 2, LocalDate.of(2024, 10, 5), LocalTime.of(1, 9, 22), LocalTime.of(1, 9, 24));
        ses4 = new Session(50, 2, 50, 3.5f, 1756, LocalDate.of(2024, 10, 19), LocalTime.of(22, 21, 21), LocalTime.of(22, 50, 37));
        ses5 = new Session(100, 37, 44, 4.0f, 119, LocalDate.of(2024, 9, 22), LocalTime.of(11, 9, 11), LocalTime.of(11, 11, 10));
    }

    @Test
    void testConstructor() {
        assertEquals(song1.getTitle(), "Intermezzo op.118 no. 2");
        assertEquals(song1.getComposer(), "Brahms");
        assertEquals(song1.getInstrument(), "Piano");
        assertEquals(song1.getBarNumber(), 117);
        assertEquals(song1.getTargetTempo(), 72);
    }

    @Test
    void testLogSessionToSessionList() {
        assertEquals(song1.getSessions().size(), 0);
        song1.logSessionToSessionList(ses1);
        assertEquals(song1.getSessions().size(), 1);
        song1.logSessionToSessionList(ses2);
        assertEquals(song1.getSessions().size(), 2);

        assertEquals(song1.getSessions().get(0).getTempo(), 50);
        assertEquals(song1.getSessions().get(1).getTempo(), 100);
    }

    @Test
    void testLogSessionToDayFirstSession() {
        assertEquals(song1.getDays().size(), 0);
        song1.logSessionToDay(ses1);
        assertEquals(song1.getDays().size(), 1);
        assertEquals(song1.getDays().get(0).getSessionList().size(), 1);
        song1.logSessionToDay(ses2);
        assertEquals(song1.getDays().size(), 2);
        assertEquals(song1.getDays().get(1).getSessionList().size(), 1);
        song1.logSessionToDay(ses3);
        assertEquals(song1.getDays().size(), 2);
        assertEquals(song1.getDays().get(0).getSessionList().size(), 2);
    }

    @Test 
    void testMonthlyProgressSummary() {
        song2.logSessionToDay(ses1);
        song2.logSessionToDay(ses2);
        song2.logSessionToDay(ses3);
        song2.logSessionToDay(ses4);
        song2.logSessionToDay(ses5);

        List<Day> listDay1 = song2.monthlyProgressSummary(Month.MARCH, 2000);
        assertEquals(listDay1.size(), 0);

        List<Day> listDay2 = song2.monthlyProgressSummary(Month.OCTOBER, 2024);
        assertEquals(listDay2.size(), 3);

        List<Day> listDay3 = song2.monthlyProgressSummary(Month.OCTOBER, 1999);
        assertEquals(listDay3.size(), 1);

        List<Day> listDay4 = song2.monthlyProgressSummary(Month.SEPTEMBER, 2024);
        assertEquals(listDay4.size(), 1);
    }

}
