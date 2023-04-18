package tn.esprit.pidevcrashcode.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pidevcrashcode.Entities.Message;

public interface MessageRepository extends JpaRepository<Message,Integer> {
}
