package allaboutecm.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class MusicalInstrumentUnitTest {

    private MusicalInstrument musI;


    @BeforeEach
    public void setUp() {
        musI = new MusicalInstrument("Piano");
    }

    @DisplayName("MusicalInstrument Should Not Be Null")
    @Test
    public void shouldConstructAlbum() {
        assertNotNull(musI, "Musical Instrument object should not be null");
    }

    @DisplayName("setMusician Instrument Name with null argument should throw null pointer exception")
    @Test
    public void shouldThrowNullPointerExceptionWhenMusicalInstrumentNameSetNull() {
        assertThrows(NullPointerException.class, () -> musI.setName(null));
    }

    @ParameterizedTest
    @DisplayName("Should reject improper Musical Instrument name with multiple letters")
    @ValueSource(strings = {"1212", "@", "$", "_", "   F", "F   ", "f12"})
    public void shouldThrowIllegalArgumentExceptionWhenAlbumNameIsSetALetter(String args) {
        assertThrows(IllegalArgumentException.class, () -> musI.setName(args));
    }


    @DisplayName("Should accept proper musical instrument name")
    @ParameterizedTest
    @ValueSource(strings = {"Piano", "Accordion", "Piccolo Clarinet"})
    public void shouldAcceptProperMusicalInstrumentName(String args)
    {
        musI.setName(args);
        assertTrue(args.equals(musI.getName()));
    }
}
