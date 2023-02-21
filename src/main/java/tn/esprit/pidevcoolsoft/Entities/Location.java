package tn.esprit.pidevcoolsoft.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLocation;
    private String name;
    private String state;
    private String town;
    @Column(nullable = true)
    private String longitude;
    @Column(nullable = true)
    private String lattitude;

    @OneToMany( mappedBy="location")
    private Set<CampingCenter> campingCenters;

    @OneToMany(mappedBy = "location")
    private Set<Order> orders;

    @OneToMany(mappedBy = "location")
    private Set<User> users;
}
