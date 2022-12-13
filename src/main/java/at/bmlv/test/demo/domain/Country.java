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

@Entity(name = "country")
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_gen")
    @SequenceGenerator(name = "country_gen", sequenceName = "country_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "land", nullable = false, unique = true)
    private String countryName;
    @Column(name = "landabkürzung", nullable = false, unique = true)
    private String countryNameAbbreviation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Country country = (Country) o;
        return id != null && Objects.equals(getId(), country.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCountryName(), getCountryNameAbbreviation());
    }
}
