package allaboutecm.model;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class Track extends Entity{
    private String name;
    private String length;




    public Track(String name, String length) {
        notNull(name);
        notNull(length);

        notBlank(name);
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


//

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        if (null == length) {
            throw new NullPointerException("Track length can not be null");
        }
        if (!length.matches("^(?:([0-9]?\\d):)?([0-5]?\\d)$")) {
            throw new IllegalArgumentException("Not a valid track length");
        }
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return name.equals(track.name) &&
                length.equals(track.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, length);
    }

}







