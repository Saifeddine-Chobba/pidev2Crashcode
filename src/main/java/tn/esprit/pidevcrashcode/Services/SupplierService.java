package tn.esprit.pidevcrashcode.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.Supplier;
import tn.esprit.pidevcrashcode.Repositories.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService implements ISupplierService{

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public void saveSupplier(Supplier supplier) {
         supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplier(Supplier supplier) {
        supplierRepository.delete(supplier);
    }

    @Override
    public void updateSupplier(Supplier supplierOld, Supplier supplierNew) {
        supplierOld.setEmail(supplierNew.getEmail());
        supplierOld.setName(supplierNew.getName());
        supplierOld.setTelephone(supplierNew.getTelephone());
        supplierOld.setSupplies(supplierNew.getSupplies());
        saveSupplier(supplierOld);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Optional<Supplier> getSupplierById(int id) {
        return supplierRepository.findById(id);
    }

}
