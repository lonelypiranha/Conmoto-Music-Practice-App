package persistence;

import model.Day;
import model.Session;
import model.Song;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class JsonTest {
    protected void checkSong(String title, String composer, String instrument, int barCount, int tempo, List<Session> sessions, List<Day> days, Song song) {
        assertEquals(title, song.getTitle());
        assertEquals(composer, song.getComposer());
        assertEquals(instrument, song.getInstrument());
        assertEquals(barCount, song.getBarNumber());
        assertEquals(tempo, song.getTargetTempo());

        assertEquals(sessions.size(), song.getSessions().size());
        assertEquals(days.size(), song.getDays().size());

        for (int i = 0; i < sessions.size(); i++) {
            checkSession(sessions.get(i), song.getSessions().get(i));
        }

        for (int i = 0; i < days.size(); i++) {
            checkDays(days.get(i), song.getDays().get(i));
        }
    }

    private void checkSession(Session s1, Session s2) {
        assertEquals(s1.getTempo(), s2.getTempo());
        assertEquals(s1.getStartBar(), s2.getStartBar());
        assertEquals(s1.getEndBar(), s2.getEndBar());
        assertEquals(s1.getOverallMastery(), s2.getOverallMastery());
        assertEquals(s1.getDuration(), s2.getDuration());
        assertEquals(s1.getPracticeDate(), s2.getPracticeDate());
        assertEquals(s1.getStartTime(), s2.getStartTime());
        assertEquals(s1.getEndTime(), s2.getEndTime());
        
    }

    private void checkDays(Day d1, Day d2) {
        assertEquals(d1.getSessionList().size(), d2.getSessionList().size());
        for (int i = 0; i < d1.getSessionList().size(); i++) {
            checkSession(d1.getSessionList().get(i), d2.getSessionList().get(i));
        }
        assertEquals(d1.getLocalDate(), d2.getLocalDate());
    }
}
