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
public class Rating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRating;
    private int idUser;
    private int ratingValue;  //controle saisie --> 1 jusqua 5
    @Temporal(TemporalType.DATE)
    private Date ratingDate;
    @ManyToOne
    CampingCenter campingCenter;
}
