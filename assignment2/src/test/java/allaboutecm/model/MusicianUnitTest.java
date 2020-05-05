package allaboutecm.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.URL;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MusicianUnitTest {
    private Musician mus;

    @BeforeEach
    public void setUp1() {
        mus = new Musician("Lucy Railton");
    }

    @Test
    @DisplayName("Musician name cannot be null and should throw null pointer exception")
    public void musicianNameCannotBeNull()
    {
        assertThrows(NullPointerException.class, () -> mus.setName(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    \t"})
    @DisplayName("Musician name cannot be empty or blank")
    public void MusicianNameCannotBeEmptyOrBlank(String arg) {
        assertThrows(IllegalArgumentException.class, () -> mus.setName(arg));
    }

    @DisplayName("Should accept proper Musician name")
    @ParameterizedTest
    @ValueSource(strings = {"Gianluigi Trovesi", "Gianni Coscia", "Herman Hupfeld"})
    public void shouldAcceptProperMusicianName(String args) {
        mus.setName(args);
        assertTrue(args.equals(mus.getName()));
    }

    @DisplayName("Should reject improper Musician name with multiple invalid letters")
    @ParameterizedTest
    @ValueSource(strings = {"1212", "@", "$", "_", "   F", "F   ", "f12"})
    public void shouldThrowIllegalArgumentExceptionWhenTrackNameIsSetALetter(String args)
    {
        assertThrows(IllegalArgumentException.class, () -> mus.setName(args));
    }

    @Test
    @DisplayName("should return a musician name")
    public void shouldGetMusicianName() {
        Musician mus2 = new Musician("Lucy Railton");
        assertTrue(mus.getName().equals(mus2.getName()),"getName method for Musician class executed successfully");
    }

    //test cases for musician URL
    //Todo: With null argument, it should throw illegal argument exception


    @Test
    @DisplayName("If wrong musician URL is entered which is not responsive an exception should be thrown")
    public void shouldThrowUnknownHostExceptionWhenInvalidURLIsSet()
    {
        assertThrows(UnknownHostException.class, () -> mus
                .setMusicianUrl(new URL("https://www.goasdfasdfasdfaogle.com")));
    }
}



