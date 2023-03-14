package tn.esprit.pidevcrashcode.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevcrashcode.Entities.Location;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    // Add any additional methods specific to the Invoice entity if needed

    Optional<List<Location>> findAllByState(String state);

    Optional<List<Location>> findAllByTown(String town);

    Optional<Location> findByLongitudeAndLattitude(float longitude, float latitude);

    Optional<Location> findByName(String name);

    List<Location> findAllByLongitude(float longitude);
}
