package tn.esprit.pidevcrashcode.Services;

import tn.esprit.pidevcrashcode.Entities.Supplier;

import java.util.List;
import java.util.Optional;

public interface ISupplierService {

    void saveSupplier(Supplier supplier);

    void deleteSupplier(Supplier supplier);

    void updateSupplier(Supplier supplierOld, Supplier supplierNew);

    List<Supplier> getAllSuppliers();

    Optional<Supplier> getSupplierById(int id);
}
