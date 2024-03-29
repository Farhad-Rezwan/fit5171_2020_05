package allaboutecm.model;

import com.google.common.collect.Sets;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Set;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * An artist that has been featured in (at least) one ECM record.
 *
 * See {@https://www.ecmrecords.com/artists/1435045745}
 */
public class Musician extends Entity {
    private String name;

    private URL musicianUrl;

    private Set<Album> albums;


    public Musician(String name) {

        notBlank(name);
        notNull(name);


        this.name = name;
        this.musicianUrl = null;


        albums = Sets.newLinkedHashSet();
    }

    public void setName(String name) {


        if (name==null) {
            throw new NullPointerException("musician name cannot be null or empty");
        }
        if (!name.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")) {
            throw new IllegalArgumentException("Not a valid musician name");
        }

        notNull(name);
        notBlank(name);
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {

        this.albums = albums;
    }
    public URL getMusicianUrl() {
        return musicianUrl;
    }

    public void setMusicianUrl(URL musicianUrl) throws IOException {
        HttpURLConnection connection = (HttpURLConnection)musicianUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        if (connection.getResponseCode() == 401)
        {
            throw new UnknownHostException("album URL is invalid");
        }
        this.musicianUrl = musicianUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Musician that = (Musician) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
