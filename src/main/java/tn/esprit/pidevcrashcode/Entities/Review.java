package tn.esprit.pidevcrashcode.Entities;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReview;
    @Column(length = 500)
    private String content;

    @Temporal(TemporalType.DATE)
    private Date reviewDate;
    @ManyToOne
    CampingCenter campingCenter ;
    @ManyToOne
    User user;

    //tnajem tkhali user yekteb review ken mayabda andou el camping center atheka fi wost list  visited mte3ou

}
