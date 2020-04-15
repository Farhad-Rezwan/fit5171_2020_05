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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AlbumUnitTest {
    private Album album;

    @BeforeEach
    public void setUp() {
        album = new Album(1975, "ECM 1064/65", "The Köln Concert");
    }
    //  Add a test to verify thai the album object is not null
    @Test
    public void shouldConstructAlbum() {
        assertNotNull(album, "Album object should not be null");
    }

    //    test classes for getRecordNumber
    @Test
    public void getAlbumShouldReturnString() {
        assertEquals("ECM 1064/65", album.getRecordNumber());
    }

//    @Test
//    public void recordNumberShouldBeAlphanumeric() {
//        Album album2 = new Album(1222, "ECM 1064/65", "The");
//        System.out.println(StringUtils.isAlphanumeric(album.getRecordNumber().replaceAll("\\s+","")));
//        assertTrue(StringUtils.isAlphanumeric("adfasdf 1212".replaceAll("\\s+","")));
//    }


    //    test cases for setRecordNumber
    /* ------------------------------------------------------------------------------------------
     * Todo: 1. With null argument, it should throw illegal argument exception
     *       2. Should Only accept alphanumeric characters, and can contain space or forward slash.
     *       3. Should only accept predefined prefix.
     */

    @Test
    public void shouldThrowExceptionWhenRecordNumberSetToNull() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> album.setRecordNumber(null));
        assertEquals("Record Number can not be null", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "&", "%"})
    public void recordNumberCanOnlyAcceptAlphanumericWithSpaceORWithForwardSlash(String args){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> album.setRecordNumber(args));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ECM1211", "IDONTKNO 1212"})
    public void recordNumberShouldOnlyAcceptPredefinedPrefixWithSpace(String args) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->album.setRecordNumber(args));
    }

    @ParameterizedTest
    @ValueSource(strings = {"XtraWatt1212", "ECM XYZA"})
    public void recordNumberShouldOnlyAcceptSuffixOfNumber(String args) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->album.setRecordNumber(args));
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


    //    test cases for getFeaturedMusicians

    //    test cases for setFeaturedMusicians
    /* ------------------------------------------------------------------------------------------
     * Todo: With null argument, it should throw illegal argument exception
     */
    @Test
    public void shouldThrowExceptionWhenSetFeatureMusicianSetToNull() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> album.setFeaturedMusicians(null));
        assertEquals("Featured musician list cannot be null", e.getMessage());
    }

    //    test cases for getInstruments


    //    test cases for setInstruments
    /* ------------------------------------------------------------------------------------------
     * Todo: With null argument, it should throw illegal argument exception
     */

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


}