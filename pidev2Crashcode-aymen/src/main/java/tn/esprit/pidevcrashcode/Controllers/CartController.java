package tn.esprit.pidevcrashcode.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.Cart;
import tn.esprit.pidevcrashcode.Services.CartService;

@RestController
@RequestMapping("/client/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("")
    public List<Cart> getAllCarts() {
        return cartService.findAllCarts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable int id) {
        Optional<Cart> optionalCart = cartService.findCartById(id);
        if (optionalCart.isPresent()) {
            return new ResponseEntity<>(optionalCart.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        cartService.saveCart(cart);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable int id, @RequestBody Cart cart) {
        Optional<Cart> optionalCart = cartService.findCartById(id);
        if (optionalCart.isPresent()) {
            Cart existingCart = optionalCart.get();
            existingCart.setAmounts(cart.getAmounts());
            cartService.saveCart(existingCart);
            return new ResponseEntity<>(existingCart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCartById(@PathVariable int id) {
        Optional<Cart> optionalCart = cartService.findCartById(id);
        if (optionalCart.isPresent()) {
            cartService.deleteCartById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
