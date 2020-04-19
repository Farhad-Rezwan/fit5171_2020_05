package allaboutecm.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MusicalInstrumentUnitTest {

    private MusicalInstrument musI;


    public void setup() {
        MusicalInstrument musI = new MusicalInstrument("Piano");
    }

    @DisplayName("setMusician Instrument Name with null argument should throw null pointer exception")
    @Test
    public void shouldThrowNullPointerExceptionWhenMusicalInstrumentNameSetNull() {
        assertThrows(NullPointerException.class, () -> musI.setName(null));
    }

    @ParameterizedTest
    @DisplayName("Should reject improper Musical Instrument name with multiple letters")
    @ValueSource(strings = {"1212", "@", "$", "_", "   F", "F   ", "f12"})
    public void shouldThrowIllegalArgumentExceptionWhenMusicalInstrumentNameIsSetALetter(String args) {

        musI.setName(args);
        assertThrows(IllegalArgumentException.class, () -> musI.getName());
    }

    @DisplayName("Should accept proper musical instrument name")
    @ParameterizedTest
    @ValueSource(strings = {"HONEY FOUNTAIN", "HIDDEN CHAMBER", "King Kunter", "El-pardo'n", "Farhad's November Rain"})
    public void shouldAcceptProperTrackName(String args) {
        musI.setName(args);
        assertTrue(args.equals(musI.getName()));
    }
}
