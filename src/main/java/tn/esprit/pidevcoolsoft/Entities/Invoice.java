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
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInvoice;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Temporal(TemporalType.TIME)
    private Date date;
    @OneToOne
    private Order order;
}
