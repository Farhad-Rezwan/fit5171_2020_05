package allaboutecm.model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Objects;

import static org.apache.commons.lang3.Validate.*;

public class Review extends Entity {
    private URL websiteURL;
    private Double rating;
    private String review;



    public Review(URL websiteURL, Double rating) throws IOException {
        notNull(websiteURL);
        notNull(rating);

        notNaN(rating);

        HttpURLConnection connection = (HttpURLConnection)websiteURL.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        if (connection.getResponseCode() == 401) {
            throw new UnknownHostException("website URL is invalid");
        }
        if (connection.getResponseCode() == 200) {
            this.websiteURL = websiteURL;
        }

        this.rating = rating;

        review = null;
    }

    public URL getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(URL websiteURL) throws IOException {
        if (websiteURL == null) {
            throw new NullPointerException("Website URL cannot be null");
        }

        HttpURLConnection connection = (HttpURLConnection)websiteURL.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        if (connection.getResponseCode() == 401) {
            throw new UnknownHostException("website URL is invalid");
        }
        if (connection.getResponseCode() == 200) {
            this.websiteURL = websiteURL;
        }
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        if (null == rating) {
            throw new NullPointerException("Rating value should not be null");
        }
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating should hold valid range");
        }
        this.rating = rating;
    }
    public void setRating(int rating) {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating should hold valid range");
        }

        this.rating = Double.valueOf((rating));
    }
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        if (null == review){
            throw new NullPointerException("Review cannot be null or empty");
        }
        notBlank(review);

        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return websiteURL.equals(review.websiteURL) &&
                rating.equals(review.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(websiteURL, rating);
    }

}
