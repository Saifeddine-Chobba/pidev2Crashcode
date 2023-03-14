package tn.esprit.pidevcrashcode.Services;

import tn.esprit.pidevcrashcode.Entities.Deposit;
import tn.esprit.pidevcrashcode.Entities.Location;
import tn.esprit.pidevcrashcode.Entities.Product;

import java.util.List;
import java.util.Optional;

public interface IDepositService {

    void saveDeposit(Deposit deposit);

    void deleteDeposit(Deposit deposit);

    void updateDeposit(Deposit depositOld, Deposit depositNew);

    Optional<Deposit> findDepositByName(String name);

    Optional<List<Deposit>> findDepositsByLocation(Location location);

    Optional<Deposit> findDepositById(int id);

    int getAvailableQuantityOfProduct(Product product,Deposit deposit);




}
