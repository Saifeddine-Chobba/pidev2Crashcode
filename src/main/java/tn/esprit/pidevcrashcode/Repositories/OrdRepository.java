package tn.esprit.pidevcrashcode.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevcrashcode.Entities.Ord;

@Repository
public interface OrdRepository extends JpaRepository<Ord, Integer> {
    // Add any additional methods specific to the Order entity if needed
}
