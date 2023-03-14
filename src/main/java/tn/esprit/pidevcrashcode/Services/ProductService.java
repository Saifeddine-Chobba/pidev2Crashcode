package tn.esprit.pidevcrashcode.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.Product;
import tn.esprit.pidevcrashcode.Entities.TypeActivity;
import tn.esprit.pidevcrashcode.Repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void updateProduct(Product productOld, Product productNew) {
        productOld.setName(productNew.getName());
        productOld.setCategory(productNew.getCategory());
        productOld.setPrice(productNew.getPrice());
        productOld.setDiscountPercent(productNew.getDiscountPercent());
        saveProduct(productOld);
    }

    @Override
    public Product findProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findProductsByCategory(TypeActivity category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public float getDiscountedPrice(Product product){
        float price = product.getPrice();
        float discountPercent = product.getDiscountPercent();
        return price * discountPercent / 100 ;
    }

    @Override
    public boolean isAvailable(Product product) {
        if (product.getDeposits().isEmpty()){
            return  false;
        }
        return  true;
    }
}
