package tn.esprit.pidevcrashcode.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduct;
    @Column(unique = true)
    private String name;
    private TypeActivity category;
    private float price;
    private float discountPercent;
    private boolean available = true;


    @JsonIgnore
    @ManyToMany(mappedBy = "products",cascade = CascadeType.REMOVE)
    private List<Ord> orders;

    @JsonIgnore
    @ManyToMany(mappedBy = "products",cascade = CascadeType.REMOVE)
    private Set<Cart> carts;
    @JsonIgnore
    @ManyToMany(mappedBy = "products",cascade = CascadeType.REMOVE)
    private Set<Deposit> deposits;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private Set<Supplier> suppliers;

}
