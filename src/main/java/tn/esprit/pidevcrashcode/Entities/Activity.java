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
public class Activity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idActivity;
    private String nameActivity;
    @Enumerated(EnumType.STRING)
    private TypeActivity typeActivity;
    private float ratingActivity; //moyenne tout les ratings
    private String description;
    @Column(length = 10000)
    private String image;

    @OneToMany(mappedBy = "activity" , cascade = CascadeType.REMOVE,orphanRemoval = true)
    private Set<ActivityRating> activityRatings = new HashSet<>();
    @ManyToMany(mappedBy = "favoriteActivities",cascade = CascadeType.REMOVE)
    private Set<User> favoringUserSet;
//    @ManyToMany(mappedBy = "supportedActivities")
//    private List<CampingCenter> campingCenters;
}
