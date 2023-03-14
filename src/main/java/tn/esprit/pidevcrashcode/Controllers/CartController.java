package tn.esprit.pidevcrashcode.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.Cart;
import tn.esprit.pidevcrashcode.Entities.Product;
import tn.esprit.pidevcrashcode.Services.CartService;
import tn.esprit.pidevcrashcode.Services.IProductService;

@RestController
@RequestMapping("/cart/client")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private IProductService productService;

    @GetMapping("")
    public ResponseEntity<Cart> showCart() {
        Cart cart = cartService.getCart();
        return  ResponseEntity.status(HttpStatus.OK).body(cart);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProductFromCart(@PathVariable int id) {
        Product product = productService.findProductById(id);
        cartService.deleteProductFromCart(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<String> addProductToCart(@PathVariable int id) {
        Product product = productService.findProductById(id);
        cartService.addProductToCart(product);
       return  ResponseEntity.status(HttpStatus.OK).body("product  " + product.getName() + "  succesfully added to cart");
    }

    @PutMapping("/products/updateamount/{id}")
    public ResponseEntity<Void> updateAmount(@PathVariable int id, @RequestParam("quantity") int quantity) {
        Product product = productService.findProductById(id);
       cartService.updateAmount(product,quantity);
       return new ResponseEntity<>(HttpStatus.OK);
    }


}
