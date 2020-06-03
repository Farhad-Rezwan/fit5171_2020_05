package allaboutecm.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicianInstrumentUnitTest {

    private MusicianInstrument musicianInstrument;

    @BeforeEach
    //initialize
//    public void setUp() {
//        Musician musician = new Musician("Bill White");
//        MusicalInstrument musicalInstrument = new MusicalInstrument("Piano");
//        musicianInstrument = new MusicianInstrument( musician, musicalInstrument);
//    } have to setup properly.................

    @Test
    @DisplayName("musician cannot be null")
    public void musicianCannotBeNull() {
        assertThrows(NullPointerException.class, () -> musicianInstrument.setMusician(null));
    }


//    @Test
//    @DisplayName("musical Instrument cannot be null")
//    public void musicalInstrumentCannotBeNull() {
//        assertThrows(NullPointerException.class, () -> musicianInstrument.setMusicalInstrument(null));
//    } have to setup properly............

//    @Test
//    @DisplayName("Same Musician And Musical Instrument Means Same Musician Instrument")
//    public void sameMusicianAndMusicalInstrumentMeansSameMusicianInstrument() {
//        Musician musician1 = new Musician("Bill White");
//        MusicalInstrument musicalInstrument1 = new MusicalInstrument("Piano");
//        MusicianInstrument musicianInstrument1 = new MusicianInstrument( musician1, musicalInstrument1);
//
//        assertEquals(musicianInstrument, musicianInstrument1);
//    } Have to setup properly............

//    @Test
//    @DisplayName("should return a musician as an object")
//    public void shouldGetMusician() {
//        Musician musician1 = new Musician("Bill White");
//        assertTrue(musicianInstrument.getMusician().equals(musician1),"getMusician method execute successfully");
//    } Have to setup properly

//    @Test
//    @DisplayName("should return a musicalInstrument as an object")
//    public void shouldGetMusicalInstrument() {
//        MusicalInstrument musicalInstrument1 = new MusicalInstrument("Piano");
//        assertTrue(musicianInstrument.getMusicalInstrument().equals(musicalInstrument1),"getMusicalInstrument method execute successfully");
//    } Have to setup properly..........


}
