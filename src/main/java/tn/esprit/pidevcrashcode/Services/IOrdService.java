package tn.esprit.pidevcrashcode.Services;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.aspectj.weaver.ast.Or;
import tn.esprit.pidevcrashcode.Entities.*;

import java.util.List;

public interface IOrdService {
    void saveOrder(Ord order);

    void updateStatus(Ord order, Status status);

    List<Ord> findAllOrders();

    List<Ord> findOrdersByUser(User user);

    Ord findOrderById(int id);

    void chooseMethod(Ord order, PaymentMethod method);

    void cancelOrder(Ord order);

    void extractProducts(Ord order);

    List<Ord> getAllMisions();
    List<Ord> getDailyMisions(User deliveryPerson) throws InterruptedException;

    List<Ord> getIncompleteMissions(User deliveryPerson);

    void confirmDelivery(Ord ord);

    int getCompletionTime(List<Ord> missions , Deposit deposit) throws InterruptedException;

    List<Ord> getNearbyMissions(Deposit deposit);

    Session getStripeSession(Ord ord) throws StripeException;



}
