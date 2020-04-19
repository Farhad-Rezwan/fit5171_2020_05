package allaboutecm.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AlbumUnitTest {
    private Album album;



    @BeforeEach
    public void setUp() {
        album = new Album(1975, "ECM 1064/65", "The Köln Concert");
    }

    @DisplayName("Album object Should Not Be Null")
    @Test
    public void shouldConstructAlbum() {
        assertNotNull(album, "Album object should not be null");
    }

    @Test
    public void sameNameAndNumberMeansSameAlbum() {
        Album album1 = new Album(1975, "ECM 1064/65", "The Köln Concert");

        assertEquals(album, album1);
    }

    @DisplayName("Record number should return proper value while adding and updating")
    @Test
    public void recordNumberShouldReturnProperValueAddingAndUpdating() {
        Album album2 = new Album(2019, "EC2680", "BIG VICIOUS");
        assertTrue("EC2680".equals(album2.getRecordNumber()));
        album2.setRecordNumber("ECM 2680");
        assertTrue("ECM 2680".equals(album2.getRecordNumber()));
    }

    @DisplayName("Record number with null argument should throw NullPointerException")
    @Test
    public void shouldThrowExceptionWhenRecordNumberSetToNull() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> album.setRecordNumber(null));
        assertEquals("Record Number can not be null", e.getMessage());
    }

    @DisplayName("Record Number can only accept Alphanumeric, and should not accept special characters")
    @ParameterizedTest
    @ValueSource(strings = {"*", "&", "%"})
    public void recordNumberCanOnlyAcceptAlphanumericWithSpaceORWithForwardSlash(String args){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> album.setRecordNumber(args));
    }

    @DisplayName("Record number should only accept " +
            "predefined prefixes ie. ECM, Carmo, RJAL, " +
            "YAN, Watt, and XtraWatt, otherwise throw illegal argument exception")
    @ParameterizedTest
    @ValueSource(strings = {"ECM1211", "IDONTKNO 1212"})
    public void recordNumberShouldOnlyAcceptPredefinedPrefixWithSpace(String args) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->album.setRecordNumber(args));
    }

    @DisplayName("RecordNumber prefix is case sensitive")
    @Test
    public void shouldThrowIllegalArgumentExceptionPrefixCaseIsNotFollowed() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, ()->album.setRecordNumber("ecm 1212"));
    }


    @DisplayName("Record Number can only accept suffix of number, so IllegalArgumentException is thrown")
    @ParameterizedTest
    @ValueSource(strings = {"XtraWatt 1*12", "ECM XYZA"})
    public void recordNumberShouldOnlyAcceptSuffixOfNumber(String args) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->album.setRecordNumber(args));
    }

    @DisplayName("Record Number can only accept suffix of a number which might contain forward-slash like  \"ECM 1064/65\"")
    @ParameterizedTest
    @ValueSource(strings = {"ECM 1064/65", "XtraWatt 12/223"})
    public void shouldAcceptProperRecordNumber(String goodRecordNumbers) {
        album.setRecordNumber(goodRecordNumbers);
        assertTrue(goodRecordNumbers == album.getRecordNumber());
    }

    @DisplayName("Should throw IllegalArgumentException when record number does not have space after the prefixes")
    @Test
    public void test() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->album.setRecordNumber("XtraWatt12"));
        assertTrue("Illegal record number" == e.getMessage());
    }

    @DisplayName("Should throw NullPointerException when featured musician is set to null")
    @Test
    public void shouldThrowExceptionWhenSetFeaturedMusicianSetToNull() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> album.setFeaturedMusicians(null));
        assertEquals("Featured musician list cannot be null", e.getMessage());
    }

    @DisplayName("Same name for two musician should refer same Musician object.")
    @Test
    public void twoMusicianNamesShouldReferSameMusician() {
        Musician m = new Musician("Farhad Ullah Rezwan");
        Set<Musician> s = new HashSet<Musician>();
        s.add(m);
        album.setFeaturedMusicians(s);

        for (Iterator<Musician> musicians = s.iterator(); musicians.hasNext(); ) {
            Musician f = musicians.next();
            assertTrue(f.equals(new Musician("Farhad Ullah Rezwan")));
        }
    }

    @DisplayName("Two Musican Instrument should refer to same musician and musician instrument")
    @Test
    public void twoMusicalInstrumentShouldReferSameMusicianAndSameMusicalInstrumentOfMusicianInstrumentAttribute() {
        Musician m = new Musician("Farhad Ullah Rezwan");
        MusicalInstrument i = new MusicalInstrument("Violin");
        MusicianInstrument mi = new MusicianInstrument(m, i);

        Set<MusicianInstrument> s = new HashSet<MusicianInstrument>();
        s.add(mi);
        album.setInstruments(s);

        for (Iterator<MusicianInstrument> musicianInstruments = s.iterator(); musicianInstruments.hasNext();) {
             MusicianInstrument f = musicianInstruments.next();
             assertTrue(f.equals(new MusicianInstrument(new Musician("Farhad Ullah Rezwan"), new MusicalInstrument("Violin"))));
        }
    }

    @DisplayName("Should throw NullPointerException when instruments is set to null")
    @Test
    public void shouldThrowExceptionWhenSetInstrumentsSetToNull() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> album.setInstruments(null));
        assertEquals("Instruments list cannot be null", e.getMessage());
    }


    @DisplayName("Should Throw Unknown Host Exception when invalid URL is set")
    @Test
    public void shouldThrowUnknownHostExceptionWhenInvalidURLIsSet() {
        assertThrows(UnknownHostException.class, () -> album
                .setAlbumURL(new URL("https://www.goasdfasdfasdfaogle.com")));
    }

    @DisplayName("should throw illegal argument exception when hostname does not contains \"ecmrecords\"")
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenHostnameDoesNotContainEcmecords() {
        assertThrows(IllegalArgumentException.class, () ->album
                .setAlbumURL(new URL("https://soundcloud.com/roddyricch/the-box")));
    }

    @Test
    @DisplayName("Null pointer exception should be thrown if Album URL is set null")
    public void shouldThrowExceptionWhenAlbumURLSetToNull() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> album.setAlbumURL(null));
        assertEquals("Album URL cannot be null", e.getMessage());
    }

    @DisplayName("Should return proper albumURL when set from ECM website.")
    @Test
    public void shouldReturnProperECMURL() throws IOException {
        URL u = new URL("https://www.ecmrecords.com/catalogue/143038750696/the-koln-concert-keith-jarrett");
        album.setAlbumURL(u);
        assertEquals(album.getAlbumURL(),u);
    }

//    @Test
//    @DisplayName("Should throw Illegal Argument Exception if less than one track is set")
//    public void shouldThrowIllegalArgumentExceptionWhenLessThenOneTrackIsSet() {
//        List<String> trackList;
//        trackList = new ArrayList<>();
//        assertThrows(IllegalArgumentException.class, ()-> album.setTracks(trackList));
//    }
//
//    @Test
//    public void shouldThrowExceptionWhenTracksSetToNull() {
//        NullPointerException e = assertThrows(NullPointerException.class, () -> album.setTracks(null));
//        assertEquals("Tracks list cannot be null", e.getMessage());
//    }

    @Test
    @DisplayName("Input should be a number(year)between 1970 and current.")
    public void releaseYearShouldBeBetween1970AndCurrent() {
        assertThrows(IllegalArgumentException.class, () -> album.setReleaseYear(2021));
    }

    @Test
    @DisplayName("to check if the set value is correctly returned")
    public void shouldReturnCorrectValueWhichIsSetForReleaseYear()
    {
        assertEquals(1975, album.getReleaseYear());
    }


    @DisplayName("Should throw exceptions when pass a null into album name to setAlbumName function")
    @Test
    public void shouldThrowExceptionWhenAlbumNameSetToNull() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> album.setAlbumName(null));
        assertEquals("album name cannot be null or empty", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    \t"})
    @DisplayName("Album name cannot be empty or blank")
    public void albumNameCannotBeEmptyOrBlank(String arg) {
        assertThrows(IllegalArgumentException.class, () -> album.setAlbumName(arg));
    }

    @DisplayName("Should reject improper album name with one or multiple letters")
    @ParameterizedTest
    @ValueSource(strings = {"1212", "@", "$", "_", "   F", "F   ", "f12"})
    public void shouldThrowIllegalArgumentExceptionWhenAlbumNameIsSetALetter(String args) {
        assertThrows(IllegalArgumentException.class, () -> album.setAlbumName(args));
    }

    @DisplayName("Should accept proper album name")
    @ParameterizedTest
    @ValueSource(strings = {"LA MISTERIOSA MUSICA DELLA REGINA LOANA", "CONTE DE L'INCROYABLE AMOUR", "Oded Tzur", "Mal Waldron Trio"})
    public void shouldAcceptProperAlbumName(String args) {
        album.setAlbumName(args);
        assertTrue(args == (album.getAlbumName()));
    }






}
