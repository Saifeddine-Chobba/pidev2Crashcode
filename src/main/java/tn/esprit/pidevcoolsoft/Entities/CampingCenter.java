package tn.esprit.pidevcoolsoft.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CampingCenter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCampingCenter;
    private String name ;
    private int idOwner;
    private String pictures; //serialized List<String> contains all picture names or paths
    private float rating;
    private int capacity;
    private int availableSpots;
    private String supportedAvtivities;     //serialized list<String> of "name" of activities offered by camping site
    @Enumerated(EnumType.STRING)
    private  CampingCenterCategory category;
    private float price;
    @Column(nullable = true)
    private float discountPercent;          //input =30.5 .... fonction de calcul de prix tien compte de remise 30.5% avant affichage de prix

    //relations
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy="campingCenter")
    private Set<Rating> ratings;
    @ManyToOne
    Location location;

    @OneToMany(mappedBy="campingCenter",cascade = CascadeType.REMOVE)
    private Set<Review> reviews;


}
