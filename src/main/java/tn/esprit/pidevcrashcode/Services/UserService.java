package tn.esprit.pidevcrashcode.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.User;
import tn.esprit.pidevcrashcode.Repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    /*  use this baad manrakhou tache user kemla

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Current user not found"));
    }

     */

    public User getCurrentUser(){         //taatik el user li aaml login
        String username = "dhia";      //asnaa user w baddel el username mtaa l user li thb testi bih
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Current user not found"));
    }

}
