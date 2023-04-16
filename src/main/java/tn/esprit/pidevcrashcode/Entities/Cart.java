package tn.esprit.pidevcrashcode.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCart;  // check if cart exists otherwise make one
    private String amounts; // use idProduct to delete unecessary amounts ; serilaized array format : [[1,2],[2,55]]
    @ManyToMany()
    private List<Product> products = new ArrayList<>(); //delete manually
    @OneToOne
    private User user;




}
