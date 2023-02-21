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

public class ActivityRating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idActivityRating;
    private String username;
    private int ratingValue; //1--> 5
    @Temporal(TemporalType.DATE)
    private Date dateRating;

    @ManyToOne
    private Activity activity;
}
