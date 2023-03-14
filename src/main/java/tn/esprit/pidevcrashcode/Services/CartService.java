package tn.esprit.pidevcrashcode.Services;

import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.Cart;
import tn.esprit.pidevcrashcode.Entities.Product;
import tn.esprit.pidevcrashcode.Repositories.CartRepository;
import tn.esprit.pidevcrashcode.Repositories.UserRepository;

@Service
public class CartService implements ICartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private  IProductService productService;

    @Override
    public List<Cart> findAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCart() {
         Cart cart = userService.getCurrentUser().getCart();
         if (cart == null){
             cart = new Cart();
             cart.setUser(userService.getCurrentUser());
             saveCart(cart);
             cart = userService.getCurrentUser().getCart();
             return cart;
         }
         else{return cart;}
    }

    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }


    @Override
    public void deleteProductFromCart(Product product) {
        Cart cart = getCart();
        List<Product> products = cart.getProducts();
        List<Integer> amounts = cart.getAmounts();
        if (products.contains(product)) {
            amounts.remove(products.indexOf(product));
            products.remove(product);
            cartRepository.save(cart);
        }
    }

    @Override
    public void addProductToCart(Product product) {
        Cart cart = getCart();
        List<Product> products = cart.getProducts();
        List<Integer> amounts = cart.getAmounts();
        product.setAvailable(productService.isAvailable(product));
        if (products.contains(product)){
            int index = products.indexOf(product);
            amounts.set(index,amounts.get(index)+1);
            cart.setAmounts(amounts);
        }
        else {
            products.add(product);
            amounts.add(1);
            cart.setAmounts(amounts);
            cart.setProducts(products);
        }
        saveCart(cart);
    }

    @Override
    public void updateAmount(Product product, int quantity) {
        Cart cart = getCart();
        List<Integer> amounts = cart.getAmounts();
        amounts.set(cart.getProducts().indexOf(product),quantity);
        cart.setAmounts(amounts);
        saveCart(cart);
        }



    @Override
    public void emptyCart(){
        Cart cart = getCart();
        cart.setAmounts(new ArrayList<>());
        cart.setProducts(new ArrayList<>());
        saveCart(cart);
    }

}