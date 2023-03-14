package tn.esprit.pidevcrashcode.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.Invoice;
import tn.esprit.pidevcrashcode.Entities.Ord;
import tn.esprit.pidevcrashcode.Entities.Status;
import tn.esprit.pidevcrashcode.Entities.User;
import tn.esprit.pidevcrashcode.Services.InvoiceService;
import tn.esprit.pidevcrashcode.Services.OrdService;
import tn.esprit.pidevcrashcode.Services.UserService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrdService ordService;


    // Get a list of all invoices
    @GetMapping("/all")
    public ResponseEntity<List<Invoice>> showAllInvoices() {
        List<Invoice> invoices = invoiceService.findAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    // Find an invoice by order
    @GetMapping("/order")
    public ResponseEntity<Invoice> showInvoice(Ord order) {
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        Invoice invoice = invoiceService.findInvoiceByOrder(order);
        if (invoice == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoice);
    }

    // Find invoices by user
    @GetMapping("/client/all")
    public ResponseEntity<List<Invoice>> myInvoices(User user) {
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Invoice> invoices = invoiceService.findInvoicesByUser(user);
        return ResponseEntity.ok(invoices);
    }

    //print invoice


}
