package allaboutecm.model;

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

    //    test classes for setRecordNumber




    //    test classes for setAlbumName

    //    test classes for getFeaturedMusicians

    //    test classes for setFeaturedMusicians

    //    test classes for getInstruments


    //    test classes for setInstruments

    //    test classes for getAlbumURL

    //    test classes for setAlbumURL

    //    test classes for getTracks

    //    test classes for setTracks

    //    test classes for getReleaseYear

    //    test classes for setReleaseYear

    //    test classes for getAlbumName

    //    test classes for setAlbumName

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