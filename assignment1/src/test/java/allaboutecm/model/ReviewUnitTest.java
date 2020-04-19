package allaboutecm.model;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
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

    @DisplayName("Should throw exception when  website URL is set to null")
    @Test
    public void shouldThrowExceptionWhenWebsiteURLSetToNull() {
        assertThrows(NullPointerException.class, () -> review.setWebsiteURL(null));
    }

    @DisplayName("Should Throw Unknown Host Exception when invalid websiteURL is set")
    @Test
    public void shouldThrowUnknownHostExceptionWhenInvalidWebsiteURLIsSet() {
        assertThrows(UnknownHostException.class, () -> review.setWebsiteURL(new URL("https://www.goasdfasdfasdfaogle.com")));
    }

    @DisplayName("Should throw null pointer exception when rating is set to null")
    @Test
    public void shouldThrowNullPointerExceptionWhenRatingIsSetToNull() {
        NullPointerException e = assertThrows(NullPointerException.class, ()-> review.setRating(null));
        assertEquals("Rating value should not be null", e.getMessage() );
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

    @DisplayName("Should accept parameter of integers when required")
    @ParameterizedTest()
    @ValueSource(ints = {0, 1, 4, 5})
    public void shouldAcceptIntegerOfWholeNumber (int args) {
        review.setRating(args);
        assertTrue(review.getRating() == args);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    \t"})
    @DisplayName("Review cannot be empty or blank")
    public void reviewCannotBeEmptyOrNull(String arg) {
        assertThrows(IllegalArgumentException.class, () -> review.setReview(arg));
    }

    @DisplayName("Same ratingURL and same rating means same Review")
    @Test
    public void sameRatingURLandSameRatingMeansSameReview() throws IOException {
        Review review1 = new Review(new URL("https://www.allmusic.com/album/big-vicious-mw0003359408"), 4.0);
        assertEquals(review1,
                review);
    }


}
