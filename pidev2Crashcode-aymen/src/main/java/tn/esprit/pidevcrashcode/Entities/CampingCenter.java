package tn.esprit.pidevcrashcode.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampingCenter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCampingCenter;
    private String name ;
    private int idOwner;
    @ElementCollection
    private List<String> pictures; //list feha asemi les images mtaa camping center
    private float rating;
    private int capacity;
    private int availableSpots;
    @ElementCollection
    private List<String> supportedActivities; // list feha asemi les activite  mawjoudin fi camp center
    @Enumerated(EnumType.STRING)
    private  CampingCenterCategory category;
    private float price; //soum reservation 1 jour mtaa abd wehed
    private float discountPercent;//input =30.5 .... fonction de calcul de prix tien compte de remise 30.5% avant affichage de prix
    @ManyToOne
    Location location;
    private boolean archived = false; //yethat archivé fi blaset ma yetfassakh bch maywalich campiongcenter null fi table okhrin

    //relations
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy="campingCenter")
    private Set<Rating> ratings = new HashSet<>();

    @OneToMany(mappedBy="campingCenter",cascade = CascadeType.REMOVE)
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany(mappedBy = "visited",cascade = CascadeType.REMOVE)
    private Set<User> visitingUserSet;
    @ManyToMany(mappedBy = "favoriteCamps",cascade = CascadeType.REMOVE)
    private Set<User> favoringUserSet;


}
