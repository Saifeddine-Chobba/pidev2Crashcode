package tn.esprit.pidevcrashcode.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevcrashcode.Entities.Deposit;
import tn.esprit.pidevcrashcode.Entities.Location;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepositRepository extends JpaRepository<Deposit,Integer> {
    Optional<List<Deposit>> findAllByLocation(Location location);

    Optional<Deposit> findByName(String name);


}
