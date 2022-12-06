package at.bmlv.test.demo.repository;

import at.bmlv.test.demo.domain.Country;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query("select c from country c " +
            "where lower(c.countryName) like lower(concat('%', :search,'%')) " +
            "or lower(c.countryNameAbbreviation) like lower(concat('%', :search,'%')) ")
    List<Country> findBySearch(@Param("search") String search, Pageable page);

    @Modifying
    @Query("update country c set c.countryName = :countryName, c.countryNameAbbreviation= :countryNameAbbreviation where c.id= :id")
    void update(@Param("countryName") String countryName, @Param("countryNameAbbreviation") String countryNameAbbreviation, @Param("id") Long id);


}
