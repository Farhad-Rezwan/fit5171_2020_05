package allaboutecm.model;

import javafx.util.Pair;

import java.net.URL;
import java.util.Objects;
import java.util.Set;

public class Track extends Entity{
    private String name;
    private int length;






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
