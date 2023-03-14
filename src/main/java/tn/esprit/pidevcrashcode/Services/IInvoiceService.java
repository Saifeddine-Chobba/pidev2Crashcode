package tn.esprit.pidevcrashcode.Services;

import org.hibernate.criterion.Order;
import tn.esprit.pidevcrashcode.Entities.Invoice;
import tn.esprit.pidevcrashcode.Entities.Ord;
import tn.esprit.pidevcrashcode.Entities.Status;
import tn.esprit.pidevcrashcode.Entities.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IInvoiceService {
    void saveInvoice(Invoice invoice);

    void updateStatus(Invoice invoice, Status status);

    void updatePaymentDate(Invoice invoice, Date paymentDate);

    List<Invoice> findAllInvoices();

    Invoice findInvoiceByOrder(Ord order);

    List<Invoice> findInvoicesByUser(User user);

    Optional<Invoice> findInvoiceById(int id);
}

