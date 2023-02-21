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
public class Rating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRating;
    private int idUser;
   // private int idCampingCenter;
    private int ratingValue;  //controle saisie --> 1 jusqua 10
    @Temporal(TemporalType.DATE)
    private Date ratingDate;

    @ManyToOne
    CampingCenter campingCenter;
}
