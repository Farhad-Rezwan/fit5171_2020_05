/*
TDD Approach
1. Add All tests
2. Run All tests and confirm that the new test fails
3. Write production code to cause the test to pass
4. Run All tests again
5. Refactor code if necessary
6. Repeat

how to system will behave, To give us the better idea, comes form the specification (Oracle), 1+1 returns 2,
    Should return true when user have same email.






Best Practice writing UNIT tests.
1, Make Test Cases Independent of each other
2, Make Test Cases Independent of execution order
    Although, Can be specified in Junit
3, Test only one thing in a test
4, setup(), test(), tearDown() repeat
5, Name tests sensibly & consistently
6, Write descriptive messages in assertions or displays
7, Categorise tests
    * Unit-, Integration-, System-, or Short or Long-running
8, Do not hard-code file location in FS
9, Mock External Dependencies
    * Files, database, URL, etc.
 */
package allaboutecm.model;

import allaboutecm.model.Album;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.s;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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





    @DisplayName("Record number should return proper value while adding and updating")
    @Test
    public void recordNumberShouldReturnProperValueAddingAndUpdating() {
        Album album2 = new Album(2019, "EC2680", "BIG VICIOUS");
        assertTrue("EC2680".equals(album2.getRecordNumber()));
        album2.setRecordNumber("ECM 2680");
        assertTrue("ECM 2680".equals(album2.getRecordNumber()));

    }




    //    test cases for setRecordNumber
    /* ------------------------------------------------------------------------------------------
     * Todo: 1. With null argument, it should throw illegal argument exception
     *       2. Should Only accept alphanumeric characters, and can contain space or forward slash.
     *       3. Should only accept predefined prefix. (ie ECM, )
     *       4. Should Only Accept Suffix of Numbers.
     */

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
    @ValueSource(strings = {"ECM 1064/65", "XtraWatt 12"})
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


    //    test cases for setAlbumName
    /* ------------------------------------------------------------------------------------------
     * Todo: With null argument, it should throw illegal argument exception
     */

    @DisplayName("Should throw exceptions when pass a null into album name to setAlbumName function")
    @Test
    public void shouldThrowExceptionWhenAlbumNameSetToNull() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> album.setAlbumName(null));
        assertEquals("album name cannot be null or empty", e.getMessage());
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

    @DisplayName("Two Musical Instrument should refer to same musician and musician instrument")
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

    //    test cases for getAlbumURL



    //    test cases for setAlbumURL
    /* ------------------------------------------------------------------------------------------
     * Todo: With null argument, it should throw illegal argument exception
     */

    @Test
    void getProductList() throws IOException {
        URL url = new URL("https://www.google.com");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int code = connection.getResponseCode();
        System.out.println(code);
    }

    @Test
    public void shouldThrowUnknownHostExceptionWhenInvalidURLIsSet() {
        assertThrows(UnknownHostException.class, () -> album
                .setAlbumURL(new URL("https://www.goasdfasdfasdfaogle.com")));
    }

    @Test
    public void shouldThrowExceptionWhenAlbumURLSetToNull() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> album.setAlbumURL(null));
        assertEquals("Album URL cannot be null", e.getMessage());
    }

    //    test cases for getTracks

    //    test cases for setTracks
    /* ------------------------------------------------------------------------------------------
     * Todo: With null argument, it should throw illegal argument exception
     */

    @Test
    public void shouldThrowExceptionWhenTracksSetToNull() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> album.setTracks(null));
        assertEquals("Tracks list cannot be null", e.getMessage());
    }

    //    test cases for getReleaseYear

    //    test cases for setReleaseYear
    /* ------------------------------------------------------------------------------------------
     * Todo: With null argument, it should throw illegal argument exception
     */
//    @Test
//    public void albumReleaseYearHasToBeYear() {
//        assertThrows(IllegalArgumentException.class, () -> album.setReleaseYear(0);
//    }


    //    test cases for getAlbumName

    //    test cases for setAlbumName
    /* ------------------------------------------------------------------------------------------
     * Todo: With null argument, it should throw illegal argument exception
     */

    @Test
    @DisplayName("Album name cannot be null")
    public void albumNameCannotBeNull() {
        assertThrows(NullPointerException.class, () -> album.setAlbumName(null));
    }


    @ParameterizedTest
    @ValueSource(strings = { "Oded Tzur", "Mal Waldron Trio", "Julia Hülsmann Quartet" })
    public void checkAlbumName(String candidate){
        album.setAlbumName(candidate);
        assertEquals( candidate, album.getAlbumName());
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


    @Test
    @DisplayName("Input should be a 4 digit number")
    public void releaseYearShouldBeBetween190AndCurrent() {
        assertThrows(IllegalArgumentException.class, () -> album.setReleaseYear(2021));
    }

    @Test
    @DisplayName("to check if the set value is correctly returned")
    public void shouldReturnCorrectValueWhichIsSetForReleaseYear()
    {
        assertEquals(1975, album.getReleaseYear());
    }


}
