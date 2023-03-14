package tn.esprit.pidevcrashcode.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.Product;
import tn.esprit.pidevcrashcode.Services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> showAllProducts(){
         List<Product> products = productService.findAllProducts();
         if (products.isEmpty()){
             return ResponseEntity.notFound().build();
         }
         else return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> showProduct(@PathVariable int id){
        Product product = productService.findProductById(id);
        if (product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id ,@RequestBody Product productNew){
        if (productNew == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Product productOld = productService.findProductById(id);
        if (productOld == null){
            return ResponseEntity.notFound().build();
        }
        productService.updateProduct(productOld,productNew);
        return ResponseEntity.ok(productOld);
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        if (product == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        productService.saveProduct(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteProduct(@RequestBody Product product){
        if (product == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        productService.deleteProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/discount")
    public ResponseEntity<Product> makeDiscount(@RequestBody Product product,@RequestParam("d") float discountPercent){
        if (product == null ) {
            return ResponseEntity.badRequest().build();
        }
        product.setDiscountPercent(discountPercent);
        productService.saveProduct(product);
        return ResponseEntity.ok(product);
    }
}
