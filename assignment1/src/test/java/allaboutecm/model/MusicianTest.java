package allaboutecm.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class MusicianTest {
    private Musician mus;

    @BeforeEach
    public void setUp1() {
        mus = new Musician("Lucy Railton");
    }

    @Test
    @DisplayName("Musician name cannot be null")
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

    //test cases for musician URL
    //Todo: With null argument, it should throw illegal argument exception

    @Test
    void getMusicianURL1() throws IOException {
        URL url1 = new URL("https://www.google.com");
        HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int code1 = connection.getResponseCode();
        System.out.println(code1);
    }

        @Test
        @DisplayName("If wrong musician URL is entered which is not responsive an exception should be thrown")
        public void shouldThrowUnknownHostExceptionWhenInvalidURLIsSet()
    {
            assertThrows(UnknownHostException.class, () -> mus
                    .setMusicianUrl(new URL("https://www.goasdfasdfasdfaogle.com")));
    }
}
