package tn.esprit.pidevcoolsoft.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduct;
    private String name;
    private CampingCenterCategory category;
    private int availableQuantity;
    private float price;
    private float discountPercent;
    @ManyToMany(mappedBy = "products",cascade = CascadeType.REMOVE)
    private Set<Cart> carts;

}
