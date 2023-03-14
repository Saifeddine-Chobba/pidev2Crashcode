package tn.esprit.pidevcrashcode.Services;

import tn.esprit.pidevcrashcode.Entities.Product;
import tn.esprit.pidevcrashcode.Entities.TypeActivity;

import java.util.List;

public interface IProductService {

    void saveProduct(Product product);

    void deleteProduct(Product product);

    void updateProduct(Product productOld,Product productNew);

    Product findProductById(int id);

    List<Product> findAllProducts();

    List<Product> findProductsByCategory(TypeActivity category);

    float getDiscountedPrice(Product product);

    boolean isAvailable(Product product);
}
