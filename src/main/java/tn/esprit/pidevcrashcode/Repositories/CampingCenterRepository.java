package tn.esprit.pidevcrashcode.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevcrashcode.Entities.CampingCenter;

import java.util.List;

@Repository

public interface CampingCenterRepository extends JpaRepository<CampingCenter,Integer> {
//CampingCenter findByName(String name);
}
