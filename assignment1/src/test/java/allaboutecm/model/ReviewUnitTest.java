package allaboutecm.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

class ReviewUnitTest {
    private Review review;

    @BeforeEach
    public void setUp() throws IOException {
        review = new Review(new URL("https://www.allmusic.com/album/big-vicious-mw0003359408"), 4.0);
    }

    @DisplayName("Review object should not be null")
    @Test
    public void shouldConstructReview() {
        assertNotNull(review);
    }

    @DisplayName("Should return proper website url when set")
    @Test
    public void shouldReturnProperAlbumURLWhenSet() throws IOException {
        review.setWebsiteURL(new URL("https://www.albumoftheyear.org/album/223677-avishai-cohen-big-vicious-big-vicious.php"));
    }

    @Test
    public void shouldThrowExceptionWhenAlbumURLSetToNull() {
        assertThrows(NullPointerException.class, () -> review.setWebsiteURL(null));
    }

    @DisplayName("Should Throw Unknown Host Exception when invalid websiteURL is set")
    @Test
    public void shouldThrowUnknownHostExceptionWhenInvalidWebsiteURLIsSet() {
        assertThrows(UnknownHostException.class, () -> review.setWebsiteURL(new URL("https://www.goasdfasdfasdfaogle.com")));
    }

    @DisplayName("Should throw illegal argument exception when rating is set to negative double value")
    @ParameterizedTest()
    @ValueSource(doubles = {-2.5, -3.5})
    public void shouldThrowIllegalArgumentExceptionWhenRatingIsSetToNegative(Double rating) {
        assertThrows(IllegalArgumentException.class, () -> review.setRating(rating));
    }

    @DisplayName("Should throw illegal argument exception when rating is set to less than 0.00 or more than 5.00")
    @ParameterizedTest()
    @ValueSource(doubles = {-0.1, 5.1})
    public void shouldThrowIllegalArgumentExceptionWhenRatingValueSetOutOfRange(Double rating) {
        assertThrows(IllegalArgumentException.class, () -> review.setRating(rating));
    }

    @Test
    public void shouldAcceptIntegerOfWholeNumber () {
        review.setRating(3);
        assertTrue(3.0 == review.getRating());
    }
}
