package allaboutecm.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicianInstrumentTest {

    private MusicianInstrument musicianInstrument;

    @BeforeEach

    public void setUp() {
        Musician musician = new Musician("zkan0005musician");
        MusicalInstrument musicialInstrument = new MusicalInstrument("zkan0005musicalInstrument");
        musicianInstrument = new MusicianInstrument( musician, musicialInstrument);}

    @Test
    @DisplayName("musician cannot be null")
    public void musicianCannotBeNull() {
        assertThrows(NullPointerException.class, () -> musicianInstrument.setMusician(null));
    }


    @Test
    @DisplayName("musical Instrument cannot be null")
    public void musicalInstrumentCannotBeNull() {
        assertThrows(NullPointerException.class, () -> musicianInstrument.setMusicalInstrument(null));
    }

    @Test
    public void sameMusicianAndMusicialInstrumentMeansSameAlbum() {
        Musician musician1 = new Musician("zkan0005musician");
        MusicalInstrument musicalInstrument1 = new MusicalInstrument("zkan0005musicalInstrument");
        MusicianInstrument musicianInstrument1 = new MusicianInstrument( musician1, musicalInstrument1);

        assertEquals(musicianInstrument, musicianInstrument1);
    }

    @Test
    public void shouldGetZkan0005Musician() {
        Musician musician1 = new Musician("zkan0005musician");
        assertTrue(musicianInstrument.getMusician().equals(musician1),"getMusician method execute successfully");
        //assertTrue(musicianInstrument.getMusician().getName().equals("zkan0005musician"));
    }

    @Test
    public void shouldGetZkan0005MusicalInstrument() {
        MusicalInstrument musicalInstrument1 = new MusicalInstrument("zkan0005musicalInstrument");
        assertTrue(musicianInstrument.getMusicalInstrument().equals(musicalInstrument1),"getMusicalInstrument method execute successfully");
        //assertTrue(musicianInstrument.getMusicalInstrument().getName().equals("zkan0005musicalInstrument"));
    }




    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }
}