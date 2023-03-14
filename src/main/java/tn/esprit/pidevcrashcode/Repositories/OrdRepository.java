package tn.esprit.pidevcrashcode.Repositories;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevcrashcode.Entities.Ord;
import tn.esprit.pidevcrashcode.Entities.Status;
import tn.esprit.pidevcrashcode.Entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdRepository extends JpaRepository<Ord, Integer> {
    // Add any additional methods specific to the Order entity if needed
     List<Ord> findAllByUser(User user);

     Ord findByUser(User user);

     Optional<Ord> findByIdOrder(int id);

     List<Ord> findAllByDeliveryPersonAndStatus(User deliveryPerson, Status status);
}
