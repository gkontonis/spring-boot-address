package at.bmlv.test.demo.repository;

import at.bmlv.test.demo.domain.Country;
import at.bmlv.test.demo.domain.Place;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query("select p from place p " +
            "where lower(p.placeName) like lower(concat('%', :search,'%')) " +
            "or lower(p.state) like lower(concat('%', :search,'%')) " +
            "or str(p.postcode) like lower(concat('%', :search,'%')) "
    )
    List<Country> findBySearch(@Param("search") String search, Pageable page);

    @Modifying
    @Query("update place p set p.placeName = :placeName, p.state =:state, p.postcode = :postcode where p.id= :id")
    void update(@Param("placeName") String placeName, @Param("state") String state,@Param("postcode") Integer postcode, @Param("id") Long id);
}
