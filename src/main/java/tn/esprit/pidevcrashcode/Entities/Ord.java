package tn.esprit.pidevcrashcode.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrder;
    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Product> products;
    @ElementCollection()
    @OrderColumn(name = "ordinal")
    private List<Integer> amountsOrdered;
    @ElementCollection
    @OrderColumn(name = "ordinal")
    private List<Float> prices;
    @ElementCollection
    @OrderColumn(name = "ordinal")
    private List<Float> discounts;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod = PaymentMethod.Cash;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryTime;
    @ManyToOne
    private  User user;

    @ManyToOne
    private User deliveryPerson;

    @JsonIgnore
    @OneToOne(mappedBy = "order",cascade = CascadeType.PERSIST)
    private Invoice invoice;

    @ManyToOne()
    private Location deliveryLocation;





}
