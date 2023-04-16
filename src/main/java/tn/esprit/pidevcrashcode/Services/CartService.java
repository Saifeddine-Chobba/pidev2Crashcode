package tn.esprit.pidevcrashcode.Services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.Cart;
import tn.esprit.pidevcrashcode.Entities.Product;
import tn.esprit.pidevcrashcode.Repositories.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> findAllCarts() {
        return cartRepository.findAll();
    }

    public Optional<Cart> findCartById(int id) {
        return cartRepository.findById(id);
    }

    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    public void deleteCartById(int id) {
        cartRepository.deleteById(id);
    }

   /* public void deleteProductFromCart(Cart cart, Product product) {
        Set<Product> products = cart.getProducts();
        if (products.contains(product)) {
            products.remove(product);
            // Update the amounts field by removing the corresponding product quantity
            List<List<Integer>> amountsList = deserializeAmounts(cart.getAmounts());
            List<Integer> productAmount = Arrays.asList(product.getIdProduct(), 0);
            amountsList.remove(productAmount);
            String updatedAmounts = serializeAmounts(amountsList);
            cart.setAmounts(updatedAmounts);
            cartRepository.save(cart);
        }
    }

    // Utility method to serialize and deserialize the amounts field
    private String serializeAmounts(List<List<Integer>> amountsList) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(amountsList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<List<Integer>> deserializeAmounts(String amounts) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(amounts, new TypeReference<List<List<Integer>>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    */

}