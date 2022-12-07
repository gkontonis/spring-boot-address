package at.bmlv.test.demo.repository;

import at.bmlv.test.demo.domain.Country;
import at.bmlv.test.demo.domain.Person_Address;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Person_AddressRepository extends JpaRepository<Person_Address, Long> {
    @Query("select pa from person_address pa " +
            "where lower(pa.person) like lower(concat('%', :search,'%')) " +
            "or lower(pa.address) like lower(concat('%', :search,'%')) "
    )
    List<Person_Address> findBySearch(@Param("search") String search, Pageable page);

    /*@Modifying
    @Query("update person_address pa set pa.person = :person, pa.address= :address where pa.id= :id")
    void update(@Param("placeName") String placeName, @Param("state") String state,@Param("postcode") Integer postcode, @Param("id") Long id);*/
}
