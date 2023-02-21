package tn.esprit.pidevcoolsoft.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCart;  // check if cart exists otherwise make one
    //private List<Integer> idProducts;
    private String amounts; // use idProduct to delete unecessary amounts ; serilaized array format : [[1,2],[2,55]]

    @ManyToMany
    private Set<Product> products;
    @OneToOne
    private User user;




}
