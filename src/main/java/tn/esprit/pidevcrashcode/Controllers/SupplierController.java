package tn.esprit.pidevcrashcode.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.Supplier;
import tn.esprit.pidevcrashcode.Services.ISupplierService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    @GetMapping("/All")
    public ResponseEntity<List<Supplier>> showAllSuppliers(){
        List<Supplier> allSuppliers = supplierService.getAllSuppliers();
        if (allSuppliers.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allSuppliers);
    }

    @GetMapping("/supplier")
    public ResponseEntity<Supplier> showSupplier(Supplier supplier){
        Optional<Supplier> supplierOptional = supplierService.getSupplierById(supplier.getId());
        return supplierOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/supplier/update")
    public ResponseEntity<Void> updateSupplierInfo(Supplier old , Supplier supplierNew ){
        supplierService.updateSupplier(old,supplierNew);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/supplier/delete")
    public ResponseEntity<Void> deleteSupplier(Supplier supplier){
        supplierService.deleteSupplier(supplier);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createSupplier(Supplier supplier){
        supplierService.saveSupplier(supplier);
        return ResponseEntity.ok().build();
    }
}
