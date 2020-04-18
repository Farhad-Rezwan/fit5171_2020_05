package allaboutecm.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import javafx.util.Pair;
import org.apache.commons.lang3.ObjectUtils;

import java.net.URL;
import java.util.Objects;
import java.util.Set;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class Track extends Entity{
    private String name;
    private int length;

    public Track(String name, int length) {
        notNull(name);
        notNull(length);

        notBlank(name);

        this.name = name;
        this.length = length;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (null == name) {
            throw new NullPointerException("Track name cannot be null");
        }
        if (!name.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")) {
            throw new IllegalArgumentException("Not a valid track name");
        }

        notNull(name);
        notBlank(name);
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

//    public String toString() {
//        return String.format("%02d:%02d:%02d", hour, minute, second);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return name.equals(track.name) &&
                length == track.length;

    }



    @Override
    public int hashCode() {
        return Objects.hash(name, length);
    }

}
