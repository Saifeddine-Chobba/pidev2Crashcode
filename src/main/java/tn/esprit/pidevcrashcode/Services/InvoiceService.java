package tn.esprit.pidevcrashcode.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.Invoice;
import tn.esprit.pidevcrashcode.Entities.Ord;
import tn.esprit.pidevcrashcode.Repositories.InvoiceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {


    @Autowired
    private  InvoiceRepository invoiceRepository;
    @Autowired
    private  OrdService ordService;

    public InvoiceService(InvoiceRepository invoiceRepository, OrdService ordService) {
        this.invoiceRepository = invoiceRepository;
        this.ordService = ordService;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(int id) {
        return invoiceRepository.findById(id);
    }

    public Invoice createInvoice(Invoice invoice) {
        Ord order = ordService.getOrdById(invoice.getOrder().getIdOrder()).orElseThrow(() -> new RuntimeException("Order not found"));
        invoice.setOrder(order);
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoiceById(int id) {
        invoiceRepository.deleteById(id);
    }

}
