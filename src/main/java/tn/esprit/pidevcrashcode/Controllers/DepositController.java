package tn.esprit.pidevcrashcode.Controllers;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.Deposit;
import tn.esprit.pidevcrashcode.Services.DepositService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/deposits")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @GetMapping("/all")
    public ResponseEntity<List<Deposit>> showAllDeposits(){
        List<Deposit> deposits = depositService.findAll();
        if (deposits == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deposits);
    }

    @GetMapping("/all/deposit")
    public ResponseEntity<Deposit> showDeposit(Deposit deposit){
        if (deposit == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(deposit);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createDeposit(Deposit deposit){
        if (deposit == null){
            return ResponseEntity.badRequest().build();
        }
        depositService.saveDeposit(deposit);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/update")
    public ResponseEntity<Void> updateDeposit(Deposit depositNew){
        if (depositNew == null){
            return ResponseEntity.badRequest().build();
        }
        depositService.updateDeposit(depositNew,depositNew);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteDeposit(Deposit deposit){
        if (deposit == null){
            return ResponseEntity.badRequest().build();
        }
        depositService.deleteDeposit(deposit);
        return ResponseEntity.ok().build();
    }


}
