package allaboutecm.model;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AlbumUnitTest {
    private Album album;

    @BeforeEach
    public void setUp() {
        album = new Album(1975, "ECM 1064/65", "The Köln Concert");
    }


    //    test classes for getRecordNumber
    @Test
    public void getAlbumShouldReturnString() {
        assertEquals("ECM 1064/65", album.getRecordNumber());
    }


    //    test cases for setRecordNumber
    @Test
    public void shouldThrowExceptionWhenRecordNumberSetToNull() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> album.setRecordNumber(null));
        assertEquals("Number of records cannot be null", e.getMessage());
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