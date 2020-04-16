package allaboutecm.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    private List<String> tracks;

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
        tracks = Lists.newArrayList();


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

    public void setAlbumURL(URL albumURL) {
        if (null == albumURL) {
            throw new NullPointerException("Album URL cannot be null");
        }
        this.albumURL = albumURL;
    }

    public List<String> getTracks() {
        return tracks;
    }

    public void setTracks(List<String> tracks) {
        if (null == tracks) {
            throw new NullPointerException("Tracks list cannot be null");
        }
        this.tracks = tracks;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        if(releaseYear>1970)
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
