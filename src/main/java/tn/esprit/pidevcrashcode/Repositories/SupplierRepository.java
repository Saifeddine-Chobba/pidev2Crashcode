package tn.esprit.pidevcrashcode.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevcrashcode.Entities.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {

}
