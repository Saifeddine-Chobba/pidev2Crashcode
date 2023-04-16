package tn.esprit.pidevcrashcode.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pidevcrashcode.Entities.Document;

public interface DocumentRepository extends JpaRepository<Document,Integer> {
}
