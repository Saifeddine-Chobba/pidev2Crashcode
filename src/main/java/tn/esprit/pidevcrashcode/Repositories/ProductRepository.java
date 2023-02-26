package tn.esprit.pidevcrashcode.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevcrashcode.Entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // add any additional methods specific to this repository
}
