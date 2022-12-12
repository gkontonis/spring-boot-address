package at.bmlv.test.demo.repository;

import at.bmlv.test.demo.domain.Country;
import at.bmlv.test.demo.domain.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    @Query("select p from person p " +
            "where lower(p.firstName) like lower(concat('%', :search,'%')) " +
            "or lower(p.lastName) like lower(concat('%', :search,'%')) " +
            "or lower(p.gender) like lower(concat('%', :search,'%')) "
    )
    List<Person> findBySearch(@Param("search") String search, Pageable page);

    @Modifying
    @Query("update person p set p.firstName = :firstName, p.lastName = :lastName, p.gender= :gender where p.uuid= :id")
    void update(@Param("firstName") String firstName,
                @Param("lastName") String lastName,
                @Param("gender") Character gender,
                @Param("id") UUID id);

}
