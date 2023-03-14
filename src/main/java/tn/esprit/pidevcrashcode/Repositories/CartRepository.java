package tn.esprit.pidevcrashcode.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevcrashcode.Entities.Cart;
import tn.esprit.pidevcrashcode.Entities.Product;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart getCartByIdCart(int cartId);

}
