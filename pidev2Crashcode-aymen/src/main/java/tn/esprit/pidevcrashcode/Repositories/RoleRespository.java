package tn.esprit.pidevcrashcode.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevcrashcode.Entities.ERole;
import tn.esprit.pidevcrashcode.Entities.Role;

import java.util.Optional;


@Repository

public interface RoleRespository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
