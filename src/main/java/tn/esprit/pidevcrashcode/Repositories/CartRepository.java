package tn.esprit.pidevcrashcode.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevcrashcode.Entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
