package tn.esprit.pidevcrashcode.Controllers;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.*;
import tn.esprit.pidevcrashcode.Services.ILocationService;
import tn.esprit.pidevcrashcode.Services.InvoiceService;
import tn.esprit.pidevcrashcode.Services.OrdService;
import tn.esprit.pidevcrashcode.Services.UserService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdController {

    @Autowired
    private OrdService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ILocationService locationService;

    @PostMapping("/client/create")
    public ResponseEntity<Ord> createOrder(@RequestBody Ord order){
        orderService.extractProducts(order);
        order.setStatus(Status.Ongoing);
        order.setDate(orderService.getCurrentDate());
        order.setUser(userService.getCurrentUser());
        Invoice invoice = new Invoice();
        invoice.setOrder(order);
        invoice.setStatus(Status.Ongoing);
        order.setInvoice(invoice);
        orderService.saveOrder(order);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<Ord>> showAllOrders() {
        List<Ord> orders = orderService.findAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Ord> showOrder(@PathVariable int id) {
        Ord order = orderService.findOrderById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/client/all")
    public ResponseEntity<List<Ord>> showAllOrdersByUser() {
        User user = userService.getCurrentUser();
        List<Ord> orders = orderService.findOrdersByUser(user);
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/client/myOrder/{id}/cancel")
    public ResponseEntity<Ord> cancelOrder(@PathVariable int id ) {
        Ord order = orderService.findOrderById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (order.getStatus().equals(Status.Resolved) || order.getStatus().equals(Status.Cancelled)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        orderService.cancelOrder(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/client/myOrder/{id}/pay")
    public ResponseEntity<String> payOrder(@PathVariable int id ) {
        Ord order = orderService.findOrderById(id);
        try {
            return ResponseEntity.ok(orderService.getStripeSession(order).getId());
        } catch (StripeException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/client/myOder/configure")
    public ResponseEntity<?> configureOrder(@RequestParam PaymentMethod method , @RequestBody Location location){
        Ord order = new Ord();
        if ( location==null || method == null){
            return ResponseEntity.badRequest().body(method);
        }
        List<Location> locationsWithLongitude = locationService.getAllByLongitude(location.getLongitude()) ;
        if (!locationsWithLongitude.isEmpty()){
            order.setPaymentMethod(method);
            order.setDeliveryLocation(locationsWithLongitude.get(0));
            return ResponseEntity.ok(order);
        }

        Location finalLocation = locationService.reverseGeoccode(location.getLongitude(),location.getLattitude());
        order.setPaymentMethod(method);
        order.setDeliveryLocation(finalLocation);
        locationService.saveLocation(finalLocation);

        return ResponseEntity.ok(order);
    }

    @PutMapping("/client/myOrder/confirm")
    public  ResponseEntity<Ord> confirmDelivery(Ord ord){
        orderService.confirmDelivery(ord);
        return ResponseEntity.ok(ord);
    }



}
