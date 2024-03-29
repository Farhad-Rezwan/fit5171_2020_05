package allaboutecm.model;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Objects;
import java.util.Set;

import java.net.HttpURLConnection;


import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Represents an album released by ECM records.
 *
 * See {@https://www.ecmrecords.com/catalogue/143038750696/the-koln-concert-keith-jarrett}
 */
public class Album extends Entity {

    private int releaseYear;

    private String recordNumber;

    private String albumName;

    private Set<Musician> featuredMusicians; //unordered cannot store duplicates

    private Set<MusicianInstrument> instruments;

    private URL albumURL;

    private Set<Track> tracks;

    private Set<Review> albumReview;

    private String genre;

    private String releaseFormat;


    public Album(int releaseYear, String recordNumber, String albumName) {
        notNull(recordNumber);
        notNull(albumName);

        notBlank(recordNumber);
        notBlank(albumName);

        this.releaseYear = releaseYear;
        this.recordNumber = recordNumber;
        this.albumName = albumName;

        this.albumURL = null;

        featuredMusicians = Sets.newHashSet();
        instruments = Sets.newHashSet();
        tracks = Sets.newHashSet();
    }

    public String getRecordNumber() {

        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        String[] prefix = {"ECM ","Carmo ", "RJAL ", "YAN ", "Watt ", "XtraWatt "};
        if (null == recordNumber){
            throw new NullPointerException("Record Number can not be null");
        }
        for (int i = 0; i < 6; i++) {
            if (recordNumber.startsWith(prefix[i])){
                String numberValue = recordNumber.substring(prefix[i].length())
                        .replaceAll("/","");
                if (Character.isDigit(Integer.parseInt(numberValue))){
                    throw new IllegalArgumentException("Illegal record number");
                }
            }
            if (!StringUtils.isAlphanumeric(recordNumber
                    .replaceAll("/","")
                    .replaceAll("\\s+",""))){
                throw new IllegalArgumentException("Illegal record number");
            }
            if(!StringUtils.startsWithAny(recordNumber, prefix)) {
                throw new IllegalArgumentException("Illegal record number");
            }

        }


        notNull(recordNumber);
        notBlank(recordNumber);

        this.recordNumber = recordNumber;
    }

    public Set<Musician> getFeaturedMusicians() {

        return featuredMusicians;
    }

    public void setFeaturedMusicians(Set<Musician> featuredMusicians) {
        if (null == featuredMusicians){
            throw new NullPointerException("Featured musician list cannot be null");
        }
        this.featuredMusicians = featuredMusicians;
    }

    public Set<MusicianInstrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(Set<MusicianInstrument> instruments) {
        if (null == instruments){
            throw new NullPointerException("Instruments list cannot be null");
        }
        this.instruments = instruments;
    }

    public URL getAlbumURL() {
        return albumURL;
    }

    public void setAlbumURL(URL albumURL) throws IOException {
        if (null == albumURL) {
            throw new NullPointerException("Album URL cannot be null");
        }
        HttpURLConnection connection = (HttpURLConnection)albumURL.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        if (connection.getResponseCode() == 401){
            throw new UnknownHostException("album URL is invalid");
        }
        if (!connection.getURL().getHost().contains("ecmrecords")) {
            throw new IllegalArgumentException();
        }
        if (connection.getResponseCode() == 200) {
            this.albumURL = albumURL;
        }

    }
    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
        if (null == tracks) {
            throw new NullPointerException("Tracks list cannot be null");
        }
        this.tracks = tracks;
    }

    public Set<Review> getAlbumReview() {
        return albumReview;
    }

    public void setAlbumReview(Set<Review> albumReview) {
        if (null == albumReview) {
            throw new NullPointerException("Album Review cannot be null");
        }
        this.albumReview = albumReview;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if (null == genre) {
            throw new NullPointerException("Genre cannot be null");
        }
        if (!genre.matches("^[a-zA-Z]+(([',. -][a-zA-Z& ])?[a-zA-Z&]*)*$")) {
            throw new IllegalArgumentException("Not a valid track name");
        }

        notNull(genre);
        notBlank(genre);

        this.genre = genre;
    }

    public String getReleaseFormat() {
        return releaseFormat;
    }

    public void setReleaseFormat(String releaseFormat) {
        String[] formats = {"CD", "LP", "DVD", "BLURAY", "BOOK"};
        if (null == releaseFormat) {
            throw new NullPointerException("Release format cannot be null");
        }

        for (int i = 0; i < 5; i++) {

            if(!StringUtils.equalsAny(releaseFormat, formats)) {
                throw new IllegalArgumentException("Illegal release format");
            }

        }

        this.releaseFormat = releaseFormat;
    }


    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if((releaseYear>1970) && releaseYear<= year)
            this.releaseYear = releaseYear;
        else
            throw new IllegalArgumentException("Year should be greater than 1970");
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        if (null == albumName){
            throw new NullPointerException("album name cannot be null or empty");
        }
        if (!albumName.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")) {
            throw new IllegalArgumentException("Not a valid album name");
        }
        notNull(albumName);
        notBlank(albumName);

        this.albumName = albumName;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return releaseYear == album.releaseYear &&
                recordNumber.equals(album.recordNumber) &&
                albumName.equals(album.albumName);
    }



    @Override
    public int hashCode() {
        return Objects.hash(releaseYear, recordNumber, albumName);
    }
}
