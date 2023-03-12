package tn.esprit.pidevcrashcode.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevcrashcode.Entities.ActivityRating;

@Repository
public interface ActivityRatingRepository extends JpaRepository<ActivityRating,Integer> {
}
