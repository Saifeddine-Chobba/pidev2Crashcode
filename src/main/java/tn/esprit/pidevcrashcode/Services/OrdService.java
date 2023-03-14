package tn.esprit.pidevcrashcode.Services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.*;
import tn.esprit.pidevcrashcode.Repositories.DepositRepository;
import tn.esprit.pidevcrashcode.Repositories.OrdRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;


@Service
public class OrdService implements IOrdService {

    @Autowired
    private OrdRepository ordRepository;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IProductService productService;
    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ILocationService locationService;
    @Autowired
    private IInvoiceService invoiceService;



    @Override
    public void saveOrder(Ord order) {
        ordRepository.save(order);
    }

    @Override
    public void updateStatus(Ord order, Status status) {
        order.setStatus(status);
        ordRepository.save(order);
    }

    @Override
    public List<Ord> findAllOrders() {
        return ordRepository.findAll();
    }

    @Override
    public List<Ord> findOrdersByUser(User user) {
        return ordRepository.findAllByUser(user);
    }

    @Override
    public Ord findOrderById(int id) {
        Optional<Ord> optionalOrder = ordRepository.findByIdOrder(id);
        return optionalOrder.orElse(null);
    }

    @Override
    public void chooseMethod(Ord order, PaymentMethod method) {
        order.setPaymentMethod(method);
        saveOrder(order);
    }

    @Override
    public void cancelOrder(Ord order) {
        order.setStatus(Status.Cancelled);
        order.setDeliveryPerson(null);
        Invoice invoice = order.getInvoice();
        invoice.setStatus(Status.Cancelled);
        invoiceService.saveInvoice(invoice);
        order.setInvoice(invoice);
        saveOrder(order);
    }

    @Override
    public void extractProducts(Ord order) {
        Cart cart = cartService.getCart();
        List<Product> products = cart.getProducts();
        List<Integer> amountsCart = cart.getAmounts();
        int index = 0;
        //initialize other lists
        List<Integer> amounts = new ArrayList<>();
        List<Float> prices = new ArrayList<>();
        List<Float> discounts = new ArrayList<>();
        List<Product> products1 = new ArrayList<>();
        //fill lists
        for (Product product : products){
            prices.add(product.getPrice());
            discounts.add(product.getDiscountPercent());
        }
        products1.addAll(products);
        amounts.addAll(amountsCart);
        //assign lists to order
        order.setProducts(products1);
        order.setAmountsOrdered(amounts);
        order.setDiscounts(discounts);
        order.setPrices(prices);
    }



    @Override
    public List<Ord> getAllMisions() {
        return ordRepository.findAll();
    }

    @Override
    public List<Ord> getDailyMisions(User deliveryPerson) throws InterruptedException {
        List<Ord> dailies = getIncompleteMissions(deliveryPerson);
        if (!dailies.isEmpty() && (getCompletionTime(dailies,deliveryPerson.getDeposit()) >= 8*3600)){
            return dailies;
        }
        List<Ord> allMissions = getAllMisions();
        List<Ord> nearbyMissions = new ArrayList<>();
        nearbyMissions =     getNearbyMissions(deliveryPerson.getDeposit());
        for (Ord mission : nearbyMissions){
            dailies.add(mission);
            if (getCompletionTime(dailies,deliveryPerson.getDeposit()) >= 8*3600){
                dailies.remove(mission);
                break;
            }
        }
        return dailies;
    }

    @Override
    public List<Ord> getIncompleteMissions(User deliveryPerson) {
        return ordRepository.findAllByDeliveryPersonAndStatus(deliveryPerson,Status.Ongoing);
    }


    @Override
    public void confirmDelivery(Ord ord) {
        ord.setStatus(Status.Resolved);
        saveOrder(ord);
    }

    @Override
    public int getCompletionTime(List<Ord> missions,Deposit deposit) throws InterruptedException {
        return locationService.getBestRoute(missions,deposit).get("summary").get("routeSummary").get("travelTimeInSeconds").asInt();
    }

    @Override
    public List<Ord> getNearbyMissions(Deposit deposit) {
        List<Ord> nearbyMissions = new ArrayList<>();
        for (Ord mission : getAllMisions()){
            if (locationService.distanceBetween(mission.getDeliveryLocation(),deposit.getLocation()) <= 80*1000){
                nearbyMissions.add(mission);
            }
        }
        return nearbyMissions;
    }

    @Override
    public Session getStripeSession(Ord order) throws StripeException {
        Stripe.apiKey = "sk_test_51MAfj4ES68ocXgHIpdFomTcCjhbHmiAxj1muKgPyOj3taEtnkmP3nPXZwmwMWQUC9nq44xiAYN6RfyPY4mKmFOvn00fV8NftXF";

        User currentUser = userService.getCurrentUser();
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("name", currentUser.getFirstname() + " " + currentUser.getLastname());
        Customer customer = Customer.create(customerParams);

        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
        for (Product product : order.getProducts()) {
            lineItems.add(
                    SessionCreateParams.LineItem.builder()
                            .setName(product.getName())
                            .setQuantity(Long.valueOf(order.getAmountsOrdered().get(order.getProducts().indexOf(product))))
                            .setCurrency("usd")
                            .setAmount((long) Math.round(order.getPrices().get(order.getProducts().indexOf(product))*100))
                            .build()
            );
        }

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8080/orders/myOrder/pay/success")
                .setCancelUrl("http://localhost:8080/orders/myOrder/pay/cancel")
                .addAllLineItem(lineItems)
                .setCustomer(customer.getId())
                .build();

        try {
            return Session.create(params);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Date getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        // get the current system date
        Date currentDate = new Date();

        // convert the date to a string using the SimpleDateFormat object
        String dateString = dateFormat.format(currentDate);

        try {
            // parse the string to a date object
            Date date = dateFormat.parse(dateString);
            ZonedDateTime zonedDateTime = date.toInstant().atZone(ZoneId.of("Europe/Paris"));
            Date convertedDate = Date.from(zonedDateTime.toInstant());
            return convertedDate;
        } catch (ParseException e) {
            // handle the parse exception
            e.printStackTrace();
            return null;
        }
    }
}
