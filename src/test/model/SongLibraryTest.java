package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SongLibraryTest {
    SongLibrary lib1;
    Song song1;
    Song song2;
    Song song3;

    @BeforeEach
    void runBefore() {
        lib1 = new SongLibrary();
        song1 = new Song("Intermezzo op.118 no. 2", "Brahms", "Piano", 117, 72);
        song2 = new Song("Serenade for strings", "Tchaikovsky", "Violin", 293, 110);
        song3 = new Song("Violin Sonata no.1", "Brahms", "Violin", 293, 110);
    }

    @Test
    void testConstructor() {
        assertEquals(lib1.getSongList().size(), 0);
    }

    @Test
    void testAddSong() {
        assertEquals(lib1.getSongList().size(), 0);
        lib1.addSong(song1);
        assertEquals(lib1.getSongList().size(), 1);
        lib1.addSong(song2);
        assertEquals(lib1.getSongList().size(), 2);
        lib1.addSong(song2);
        assertEquals(lib1.getSongList().size(), 3);
    }

    @Test
    void testFilterByComposer() {
        lib1.addSong(song1);
        lib1.addSong(song2);
        lib1.addSong(song3);

        List<Song> ls1 = lib1.filterByComposer("Brahms");
        assertEquals(ls1.size(), 2);
        assertEquals(ls1.get(0).getTitle(), "Intermezzo op.118 no. 2");
        assertEquals(ls1.get(1).getTitle(), "Violin Sonata no.1");

        List<Song> ls2 = lib1.filterByComposer("Ravel");
        assertEquals(ls2.size(), 0);
    }

    @Test
    void testFilterByInstrument() {
        lib1.addSong(song1);
        lib1.addSong(song2);
        lib1.addSong(song3);

        List<Song> ls1 = lib1.filterByInstrument("Violin");
        assertEquals(ls1.size(), 2);
        assertEquals(ls1.get(0).getTitle(), "Serenade for strings");
        assertEquals(ls1.get(1).getTitle(), "Violin Sonata no.1");

        List<Song> ls2 = lib1.filterByInstrument("Clarinet");
        assertEquals(ls2.size(), 0);
    }

    @Test
    void testRemoveSongSuccessful() {
        lib1.addSong(song1);
        lib1.addSong(song2);
        lib1.addSong(song3);
        boolean x = lib1.removeSong(song2);
        assertTrue(x);
        assertEquals(lib1.getSongList().size(), 2);
        assertEquals(lib1.getSongList().get(0).getBarNumber(), 117);
        assertEquals(lib1.getSongList().get(1).getBarNumber(), 293);
    }

    @Test
    void testRemoveSongUnsuccessful() {
        lib1.addSong(song1);
        lib1.addSong(song2);
        boolean x = lib1.removeSong(song3);
        assertFalse(x);
        assertEquals(lib1.getSongList().size(), 2);
    }
    
}
