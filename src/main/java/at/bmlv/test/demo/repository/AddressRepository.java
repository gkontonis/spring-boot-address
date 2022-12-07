package at.bmlv.test.demo.repository;

import at.bmlv.test.demo.domain.Address;
import at.bmlv.test.demo.domain.Country;
import at.bmlv.test.demo.domain.Person_Address;
import at.bmlv.test.demo.domain.Place;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select a from address a " + "where lower(a.streetName) like lower(concat('%', :search,'%')) "
            //"or lower(a.houseNumber) like lower(concat('%', :search,'%')) "
            //"or str(a.flatNumber) like lower(concat('%', :search,'%')) "
    )
    List<Address> findBySearch(@Param("search") String search, Pageable page);

    @Modifying
    @Query("update address a set a.streetName = :streetName, a.houseNumber =:houseNumber, a.flatNumber=:flatNumber, a.place=:place, a.person_addressList=:person_AddressList where a.id= :id")
    void update(@Param("streetName") String streetName,
                @Param("houseNumber") Integer houseNumber,
                @Param("flatNumber") Integer flatNumber,
                @Param("place") Place place,
                @Param("person_AddressList") List<Person_Address> person_addressList,
                @Param("id") Long id);
}
