package tn.esprit.pidevcrashcode.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.Deposit;
import tn.esprit.pidevcrashcode.Entities.Ord;
import tn.esprit.pidevcrashcode.Entities.User;
import tn.esprit.pidevcrashcode.Services.IDepositService;
import tn.esprit.pidevcrashcode.Services.IOrdService;
import tn.esprit.pidevcrashcode.Services.UserService;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private IOrdService ordService;
    @Autowired
    private IDepositService depositService;
    @Autowired
    private UserService userService;

    @GetMapping("/deliveryperson/missions")
    public List<Ord> getDailyMissions() throws InterruptedException {
        return ordService.getDailyMisions(userService.getCurrentUser());
    }

    @GetMapping("/nearby/{id}   ")
    public List<Ord> getnearbymissions(@PathVariable int id){
        Deposit deposit = depositService.findDepositById(id).get();
        return  ordService.getNearbyMissions(deposit);
    }
}
