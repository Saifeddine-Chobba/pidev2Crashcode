package tn.esprit.pidevcrashcode.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevcrashcode.Entities.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Integer> {
}
