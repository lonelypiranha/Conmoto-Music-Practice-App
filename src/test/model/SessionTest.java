package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SessionTest {
    Session s1;

    @BeforeEach
    void runBefore() {
        s1 = new Session(50, 1, 50, 3.5f, 1756, LocalDate.of(2024, 10, 5), LocalTime.of(22, 21, 21),
                LocalTime.of(22, 50, 37));
    }

    @Test
    void testConstructor() {
        assertEquals(s1.getTempo(), 50);
        assertEquals(s1.getStartBar(), 1);
        assertEquals(s1.getEndBar(), 50);
        assertEquals(s1.getOverallMastery(), 3.5f);
        assertEquals(s1.getDuration(), 1756);
        assertEquals(s1.getPracticeDate(), LocalDate.of(2024, 10, 5));
        assertEquals(s1.getStartTime(), LocalTime.of(22, 21, 21));
        assertEquals(s1.getEndTime(), LocalTime.of(22, 50, 37));
    }

}
