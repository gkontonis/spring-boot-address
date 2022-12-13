package at.bmlv.test.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "place")
@Table(name = "place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "place_gen")
    @SequenceGenerator(name = "place_gen", sequenceName = "place_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "ort", nullable = false)
    private String placeName;
    @Column(name = "bundesland", nullable = false)
    private String state;
    @Column(name = "postleitzahl", nullable = false)
    private Integer postcode;

    @OneToOne(fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
    private Country country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Place place = (Place) o;
        return id != null && Objects.equals(getId(), place.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPlaceName(), getState(), getPostcode());
    }

}
