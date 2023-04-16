package tn.esprit.pidevcrashcode.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevcrashcode.Entities.CampingCenter;
import tn.esprit.pidevcrashcode.Entities.Rating;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {

    @Query("Select r from Rating r "+
    "WHERE r.campingCenter.idCampingCenter = :campsiteId ")
    List<Rating> findRatingsbycampsiteid(@Param("campsiteId") int campsiteId);
}
