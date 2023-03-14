package tn.esprit.pidevcrashcode.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.*;
import tn.esprit.pidevcrashcode.Repositories.InvoiceRepository;
import tn.esprit.pidevcrashcode.Repositories.OrdRepository;

import java.util.*;

@Service
public class InvoiceService implements IInvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private OrdRepository ordRepository;

    @Override
    public void saveInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    @Override
    public void updateStatus(Invoice invoice, Status status) {
        invoice.setStatus(status);
        invoiceRepository.save(invoice);
    }

    @Override
    public void updatePaymentDate(Invoice invoice, Date paymentDate) {
        invoice.setDate(paymentDate);
        invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> findAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice findInvoiceByOrder(Ord order) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findByOrder(order);
        return optionalInvoice.orElse(null);
    }

    @Override
    public List<Invoice> findInvoicesByUser(User user) {
        List<Ord> orders = ordRepository.findAllByUser(user);
        List<Invoice> invoices = new ArrayList<>();
        for (Ord order : orders) {
            Invoice invoice = findInvoiceByOrder(order);
            if (invoice != null) {
                invoices.add(invoice);
            }
        }
        return invoices;
    }

    @Override
    public Optional<Invoice> findInvoiceById(int id) {
        return invoiceRepository.findById(id);
    }




}

