package tn.esprit.pidevcrashcode.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.Deposit;
import tn.esprit.pidevcrashcode.Entities.Location;
import tn.esprit.pidevcrashcode.Entities.Product;
import tn.esprit.pidevcrashcode.Repositories.DepositRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DepositService implements IDepositService{

    @Autowired
    private DepositRepository depositRepository;


    @Override
    public void saveDeposit(Deposit deposit) {
         depositRepository.save(deposit);
    }

    @Override
    public void deleteDeposit(Deposit deposit) {
        depositRepository.delete(deposit);
    }

    @Override
    public void updateDeposit(Deposit depositOld, Deposit depositNew) {
        depositNew.setId(depositOld.getId());
        saveDeposit(depositNew);
    }

    @Override
    public Optional<Deposit> findDepositByName(String name) {
        return depositRepository.findByName(name);
    }

    @Override
    public Optional<List<Deposit>> findDepositsByLocation(Location location) {
        return depositRepository.findAllByLocation(location);
    }

    @Override
    public Optional<Deposit> findDepositById(int id) {
        return depositRepository.findById(id);
    }

    @Override
    public int getAvailableQuantityOfProduct(Product product, Deposit deposit) {
        Set<Product> products = deposit.getProducts();
        if (!products.contains(product)){
            return 0;
        }
        return deposit.getQuantities().get(product.getIdProduct());
    }


    public List<Deposit> findAll() {
       return depositRepository.findAll();
    }
}
