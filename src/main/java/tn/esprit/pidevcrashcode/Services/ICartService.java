package tn.esprit.pidevcrashcode.Services;

import tn.esprit.pidevcrashcode.Entities.Cart;
import tn.esprit.pidevcrashcode.Entities.Product;

import java.util.List;
import java.util.Optional;

public interface ICartService {
     void saveCart(Cart cart);
     List<Cart> findAllCarts();
     Cart getCart();
     void deleteProductFromCart( Product product);
     void addProductToCart( Product product );
     void updateAmount(Product product, int quantity);

     void emptyCart();
}
