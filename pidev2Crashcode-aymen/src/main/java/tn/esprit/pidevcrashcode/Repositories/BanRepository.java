package tn.esprit.pidevcrashcode.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pidevcrashcode.Entities.Ban;

public interface BanRepository extends JpaRepository<Ban, Long> {
}
