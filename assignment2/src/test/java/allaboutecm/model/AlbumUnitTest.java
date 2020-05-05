package allaboutecm.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AlbumUnitTest {
    private Album album;

    @BeforeEach
    public void setUp() {
        album = new Album(1975, "ECM 1064/65", "The Köln Concert");
    }

    @Test
    @DisplayName("Album name cannot be null")
    public void albumNameCannotBeNull() {
        assertThrows(NullPointerException.class, () -> album.setAlbumName(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    \t"})
    @DisplayName("Album name cannot be empty or blank")
    public void albumNameConnotBeEmptyOrBlank(String arg) {
        assertThrows(IllegalArgumentException.class, () -> album.setAlbumName(arg));
    }

    @Test
    public void sameNameAndNumberMeansSameAlbum() {
        Album album1 = new Album(1975, "ECM 1064/65", "The Köln Concert");

        assertEquals(album, album1);
    }

//    ------------------------------------
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
    public void shouldThrowExceptionWhenFeaturedMusicianSetToNull() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> album.setFeaturedMusicians(null));
        assertEquals("Featured musician list cannot be null", e.getMessage());
    }

//    @DisplayName("Same name for two musician should refer same Musician object.")
//    @Test
//    public void twoMusicianNamesShouldReferSameMusician() {
//        Musician m = new Musician("Farhad Ullah Rezwan");
//        Set<Musician> s = new HashSet<Musician>();
//        s.add(m);
//        album.setFeaturedMusicians(s);
//
//        for (Iterator<Musician> musicians = s.iterator(); musicians.hasNext(); ) {
//            Musician f = musicians.next();
//            assertTrue(f.equals(new Musician("Farhad Ullah Rezwan")));
//        }
//    }
//
//    @DisplayName("Two Musican Instrument should refer to same musician and musician instrument")
//    @Test
//    public void twoMusicalInstrumentShouldReferSameMusicianAndSameMusicalInstrumentOfMusicianInstrumentAttribute() {
//        Musician m = new Musician("Farhad Ullah Rezwan");
//        MusicalInstrument i = new MusicalInstrument("Violin");
//        MusicianInstrument mi = new MusicianInstrument(m, i);
//
//        Set<MusicianInstrument> s = new HashSet<MusicianInstrument>();
//        s.add(mi);
//        album.setInstruments(s);
//
//        for (Iterator<MusicianInstrument> musicianInstruments = s.iterator(); musicianInstruments.hasNext();) {
//            MusicianInstrument f = musicianInstruments.next();
//            assertTrue(f.equals(new MusicianInstrument(new Musician("Farhad Ullah Rezwan"), new MusicalInstrument("Violin"))));
//        }
//    } need to modify.........

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

    @DisplayName("Should throw null pointer exception when tracks is set to null")
    @Test
    public void shouldThrowNullPointerExceptionWhenTrackSetToNull() {
        NullPointerException e = assertThrows(NullPointerException.class, ()-> album.setTracks(null));
        assertEquals("Tracks list cannot be null", e.getMessage());
    }

//    @DisplayName("Same track name and length should refer to the same Track object")
//    @Test
//    public void twoTracksWithSameNameAndLengthShouldReferSameTrack() {
//        Track t = new Track("HONEY FOUNTAIN", "4:34");
//        Set<Track> s = new HashSet<Track>();
//        s.add(t);
//        album.setTracks(s);
//
//        for (Iterator<Track> tracks = s.iterator(); tracks.hasNext(); ) {
//            Track obj = tracks.next();
//            assertTrue(obj.equals(new Track("HONEY FOUNTAIN", "4:34")));
//        }
//    }
//
//    @DisplayName("Should throw null pointer exception when albumReview is set to null")
//    @Test
//    public void shouldThrowNullPointerExceptionWhenAlbumReviewSetNull() {
//        NullPointerException e = assertThrows(NullPointerException.class, ()-> album.setAlbumReview(null));
//        assertEquals("Album Review cannot be null", e.getMessage());
//    }
//
//    @DisplayName("Same website URL and rating for the album review should refer same Review object")
//    @Test
//    public void twoReviewWithSameReviewWebsiteURLandRatingShouldReturnSameReview() throws IOException {
//        Review r = new Review(new URL("https://www.allmusic.com/album/big-vicious-mw0003359408"), 4.0 );
//        Set<Review> s = new HashSet<Review>();
//        s.add(r);
//        album.setAlbumReview(s);
//
//        for (Iterator<Review> reviews = s.iterator(); reviews.hasNext();) {
//            Review obj = reviews.next();
//            assertTrue(obj.equals(new Review(new URL("https://www.allmusic.com/album/big-vicious-mw0003359408"), 4.0 )));
//        }
//    }
//
//    @DisplayName("Should throw null pointer exception when album genre is set to null")
//    @Test
//    public void shouldThrowNullPointerExceptionWhenGenreSetToNull() {
//        NullPointerException e = assertThrows(NullPointerException.class, ()-> album.setGenre(null));
//        assertEquals("Genre cannot be null", e.getMessage());
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"", " ", "    \t"})
//    @DisplayName("Genre cannot be empty or blank")
//    public void genreCannotBeEmptyOrBlank(String arg) {
//        assertThrows(IllegalArgumentException.class, () -> album.setGenre(arg));
//    }
//
//    @DisplayName("Should reject improper Genre")
//    @ParameterizedTest
//    @ValueSource(strings = {"1212", "@", "$", "_", "   F", "F   ", "f12"})
//    public void shouldThrowIllegalArgumentExceptionWhenGenreSetInvalidValues(String args) {
//        assertThrows(IllegalArgumentException.class, () -> album.setGenre(args));
//    }
//
//    @DisplayName("Should accept proper genre name or styles (can contain &)")
//    @ParameterizedTest
//    @ValueSource(strings = {"Electronic Dance Music", "Rock", "Jazz", "Dub-step", "Rhythm & Blues", "Techno"})
//    public void shouldAcceptProperGenre(String args) {
//        album.setGenre(args);
//        assertTrue(args.equals(album.getGenre()));
//    }
//
//    @DisplayName("Should throw null pointer exception when album release format is set to null")
//    @Test
//    public void shouldThrowNullPointerExceptionWhenAlbumReleaseFormatSetNull() {
//        NullPointerException e = assertThrows(NullPointerException.class, ()-> album.setReleaseFormat(null));
//        assertEquals("Release format cannot be null", e.getMessage());
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"", " ", "    \t"})
//    @DisplayName("Album release format cannot be empty or blank")
//    public void releaseFormatCannotBeEmptyOrBlank(String arg) {
//        assertThrows(IllegalArgumentException.class, () -> album.setReleaseFormat(arg));
//    }
//
//    @DisplayName("Album Release format should have predefined values")
//    @ParameterizedTest()
//    @ValueSource(strings = {"CD", "LP", "DVD", "BLURAY", "BOOK"})
//    public void shouldAcceptValidReleaseFormats (String format) {
//        album.setReleaseFormat(format);
//        assertEquals(format, album.getReleaseFormat());
//    }
//
//    @DisplayName("Album Release format should accept predefined values")
//    @ParameterizedTest()
//    @ValueSource(strings = {"CD_", "/lp", "DvD", "BlueRay1", "Text"})
//    public void shouldRejectInvalidReleaseFormats (String format) {
//        IllegalArgumentException i = assertThrows(IllegalArgumentException.class, ()-> album.setReleaseFormat(format));
//        assertEquals("Illegal release format", i.getMessage());
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