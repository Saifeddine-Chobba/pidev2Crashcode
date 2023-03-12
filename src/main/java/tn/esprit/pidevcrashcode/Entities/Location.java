package tn.esprit.pidevcrashcode.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLocation;
    private String name;
    private String state;
    private String town;
    private String longitude;
    private String lattitude;

    @OneToMany( mappedBy="location" )
    private Set<CampingCenter> campingCenters = new HashSet<>();

    @OneToMany(mappedBy = "location")
    private Set<Ord> orders = new HashSet<>();

    @OneToMany(mappedBy = "location")
    private Set<User> users = new HashSet<>();
}
