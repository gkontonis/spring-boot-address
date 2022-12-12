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
@Entity(name = "person_address")
@Table(name = "person_address")
public class Person_Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_address_gen")
    @SequenceGenerator(name = "person_address_gen", sequenceName = "person_address_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne()
    @JoinColumn(name = "address_id")
    private Address address;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj)) return false;
        Person_Address person_address = (Person_Address) obj;
        return id != null && Objects.equals(id, person_address.id);//TODO: 2 FK?
    }

    //TODO: hashcode?
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPerson(), getAddress());
    }

}
