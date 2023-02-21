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
public class Activity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idActivity;
    private String nameActivity;
    @Enumerated(EnumType.STRING)
    private TypeActivity typeActivity;
    private float ratingActivity; //moyenne tout les ratings
    @Column(nullable = true)
    private String description;

    @OneToMany(mappedBy = "activity" , cascade = CascadeType.REMOVE)
    private Set<ActivityRating> activityRatings;
}
