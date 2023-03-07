package tn.esprit.pidevcrashcode.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pidevcrashcode.Entities.Chatroom;

public interface ChatroomRepository extends JpaRepository<Chatroom,Integer> {
}
