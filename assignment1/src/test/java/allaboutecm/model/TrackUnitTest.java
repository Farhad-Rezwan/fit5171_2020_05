package allaboutecm.model;

import org.apache.commons.lang3.ObjectUtils;
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
    public void shouldThrowIllegalArgumentExceptionWhenTrackNameSetInvalidValues(String args) {
        assertThrows(IllegalArgumentException.class, () -> track.setName(args));
    }

    @DisplayName("Should accept proper track name, proper track name might include \"'\" and \"-\"")
    @ParameterizedTest
    @ValueSource(strings = {"HONEY FOUNTAIN", "HIDDEN CHAMBER", "King Kunter", "El-pardo'n", "Farhad's November Rain", "Farhad-November Rain"})
    public void shouldAcceptProperTrackName(String args) {
        track.setName(args);
        assertTrue(args.equals(track.getName()));
    }

    @DisplayName("Should throw null pointer exception when track length is set to null")
    @Test
    public void shouldThrowNullPointerExceptionWhenTrackLengthSetNull() {
        assertThrows(NullPointerException.class, ()-> track.setLength(null));
    }

    @DisplayName("Should throw IllegalArgumentExceptionWhen track length " +
            "format is not \"MM:SS\"" +
            "(assumption 99:59 is max track length)")
    @ParameterizedTest
    @ValueSource(strings = {"120:90", "09/10", "04-03"})
    public void shouldThrowIllegalArgumentExceptionWhenFormatIsNotMatched(String length) {
        assertThrows(IllegalArgumentException.class, ()->track.setLength(length));
    }

    @DisplayName("Should throw IllegalArgumentExceptionWhen track length second is set more than 59")
    @ParameterizedTest
    @ValueSource(strings = {"03:60","99:90"})
    public void shouldThrowIllegalArgumentExceptionWhenLenthSecondIsInvalid(String length) {
        assertThrows(IllegalArgumentException.class, ()->track.setLength(length));
    }

    @DisplayName("Should accept proper length of tracks")
    @ParameterizedTest
    @ValueSource(strings = {"04:00","25:10", "99:59"})
    public void shouldAcceptProperLengthofTracks(String length) {
        track.setLength(length);
        assertEquals(length, track.getLength());
    }

    @DisplayName("Should accept proper track length in minute and second format")
    @Test
    public void shouldAcceptProperTrackLengthFormatOfTime() {
        track.setLength("03:04");
    }




}
