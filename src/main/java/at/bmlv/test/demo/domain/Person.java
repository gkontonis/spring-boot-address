package at.bmlv.test.demo.domain;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "person")
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {@Parameter(name = "uuid_gen_strategy_class",value = "org.hibernate.id.uuid.CustomVersionOneStrategy")})
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID uuid;
    @Column(name = "vorname", nullable = false)
    private String firstName;
    @Column(name = "nachname", nullable = false)
    private String lastName;
    @Column(name = "geschlecht")
    private Character gender;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "person", orphanRemoval = true)
    private List<Person_Address> person_addressList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Person person = (Person) o;
        return uuid != null && Objects.equals(getUuid(), person.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getFirstName(), getLastName(), getGender());
    }
}
