package allaboutecm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Objects;
import java.util.Set;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * This class encapsulates the relationship of a musical instrument played by a musician in a given album.
 *
 * For example, in ECM 1064/65, MusicianInstrument denotes the instrument Piano being played by
 * the musician Keith Jarrett in this album.
 * See {@https://www.ecmrecords.com/catalogue/143038750696/the-koln-concert-keith-jarrett}
 *
 */
@NodeEntity
public class MusicianInstrument extends Entity {
    @JsonIgnore
    @Relationship(type="musician")
    private Musician musician;

    /**
     * CHANGE: Instead of a single MusicalInstrument, now it allows multiple
     * MusicalInstruments, to reflect the fact that a musician can play multiple
     * instruments.
     */
    @Relationship(type="musicalInstruments")
    private Set<MusicalInstrument> musicalInstruments;

    public MusicianInstrument() {
    }

    public MusicianInstrument(Musician musician, Set<MusicalInstrument> musicalInstrument) {
        notNull(musician);
        notNull(musicalInstrument);
        notEmpty(musicalInstrument);

        this.musician = musician;
        this.musicalInstruments = musicalInstrument;
    }


    public Musician getMusician() {
        return musician;
    }

    public void setMusician(Musician musician) {
        notNull(musician);
        this.musician = musician;
    }

    public Set<MusicalInstrument> getMusicalInstruments() {
        return musicalInstruments;
    }

    public void setMusicalInstruments(Set<MusicalInstrument> musicalInstruments) {
        this.musicalInstruments = musicalInstruments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicianInstrument that = (MusicianInstrument) o;
        return musician.equals(that.musician) &&
                musicalInstruments.equals(that.musicalInstruments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(musician, musicalInstruments);
    }
}
