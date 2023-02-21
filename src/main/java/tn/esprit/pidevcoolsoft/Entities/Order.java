package tn.esprit.pidevcoolsoft.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrder;
    private String nameProduct;
    private int amount;
    private float price;
    @Temporal(TemporalType.TIME)
    private Date date;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String firstname;
    private String lastname;
    private String email;
    @ManyToOne
    Location location;
    @OneToOne(mappedBy = "order")
    private Invoice invoice;


}
