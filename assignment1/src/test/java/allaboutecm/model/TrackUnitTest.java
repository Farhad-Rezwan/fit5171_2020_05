package allaboutecm.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class TrackUnitTest {
    private Track track;

    @BeforeEach
    public void setUp() {
         track = new Track("HONEY FOUNTAIN","02:04");
    }

    @DisplayName("Track object should not be null")
    @Test
    public void shouldConstructTrack() {
        assertNotNull(track, "Track object should not be null");
    }

    @DisplayName("setTrackName with null argument should throw NullPointerException")
    @Test
    public void shouldThrowNullPointerExceptionWhenTrackNameSetNull() {
        assertThrows(NullPointerException.class, () -> track.setName(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    \t"})
    @DisplayName("Track name cannot be empty or blank")
    public void trackNameCannotBeEmptyOrBlank(String arg) {
        assertThrows(IllegalArgumentException.class, () -> track.setName(arg));
    }

    @DisplayName("Should reject improper track name")
    @ParameterizedTest
    @ValueSource(strings = {"1212", "@", "$", "_", "   F", "F   ", "f12"})
    public void shouldThrowIllegalArgumentExceptionWhenTrackNameIsSetALetter(String args) {
        assertThrows(IllegalArgumentException.class, () -> track.setName(args));
    }

    @DisplayName("Should accept proper track name, proper track name might include \"'\" and \"-\"")
    @ParameterizedTest
    @ValueSource(strings = {"HONEY FOUNTAIN", "HIDDEN CHAMBER", "King Kunter", "El-pardo'n", "Farhad's November Rain", "Farhad-November Rain"})
    public void shouldAcceptProperTrackName(String args) {
        track.setName(args);
        assertTrue(args.equals(track.getName()));
    }

    @DisplayName("Should accept proper track length in minute and second format")
    @Test
    public void shouldAcceptProperTrackLengthFormatOfTime() {
        track.setLength("03:04");
    }


}
