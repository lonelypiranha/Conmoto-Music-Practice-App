package persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

import model.Day;
import model.Session;
import model.Song;
import model.SongLibrary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            SongLibrary sl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySongLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySongLibrary.json");
        try {
            SongLibrary sl = reader.read();
            assertEquals(0, sl.getSongList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderFilledSongLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderFilledSongLibrary.json");
        try {
            SongLibrary sl = reader.read();
            List<Song> songs = sl.getSongList();
            assertEquals(2, songs.size());

            Session s1 = new Session(12, 11, 50, 3.3f, 120, LocalDate.of(2024, 12, 9), LocalTime.of(22, 35, 45, 431239000), LocalTime.of(23, 1, 7, 123124000));
            Session s2 = new Session(69, 30, 100, 5, 900, LocalDate.of(2020, 2, 11), LocalTime.of(12, 22, 33, 000333000), LocalTime.of(14, 12, 54, 434544000));
            Session s3 = new Session(70, 31, 101, 6.6f, 100, LocalDate.of(2020, 2, 11), LocalTime.of(15, 22, 33, 000333000), LocalTime.of(16, 12, 54, 434544000));
            Session s4 = new Session(120, 1, 30, 7, 1009, LocalDate.of(1999, 10, 22), LocalTime.of(12, 33, 47, 900922000), LocalTime.of(17, 11, 37, 555444000));
            Session s5 = new Session(100, 2, 100, 1.1f, 550, LocalDate.of(1456, 4, 30), LocalTime.of(4, 324, 30, 234111000), LocalTime.of(7, 22, 34, 908456000));
            Day d1 = new Day(LocalDate.of(2024, 12, 9));
            Day d2 = new Day(LocalDate.of(2020, 2, 11));
            Day d3 = new Day(LocalDate.of(1999, 10, 22));
            Day d4 = new Day(LocalDate.of(1456, 4, 30));
            d1.addSession(s1);
            d1.addSession(s2);
            d2.addSession(s3);
            d3.addSession(s4);
            d4.addSession(s5);
            List<Session> ls1 = new ArrayList<>();
            List<Session> ls2 = new ArrayList<>();
            ls1.add(s1);
            ls1.add(s2);
            ls1.add(s3);
            ls2.add(s4);
            ls2.add(s5);
            List<Day> ld1 = new ArrayList<>();
            List<Day> ld2 = new ArrayList<>();
            ld1.add(d1);
            ld1.add(d2);
            ld2.add(d3);
            ld2.add(d4);

            checkSong("Sonata", "Beethoven", "Piano", 123, 78, ls1, ld1, songs.get(0));
            checkSong("Quartet", "Schubert", "Violin", 567, 132, ls2, ld2, songs.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
