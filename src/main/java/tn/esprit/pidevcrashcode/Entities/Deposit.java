package tn.esprit.pidevcrashcode.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Deposit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    private Location location;
    @ManyToMany
    private Set<Product> products;
    @ElementCollection
    private Map<Integer,Integer> quantities; //id product quantity mapping
    @JsonIgnore
    @OneToMany(mappedBy = "deposit")
    private Set<User> deliveeryStaff;
}
