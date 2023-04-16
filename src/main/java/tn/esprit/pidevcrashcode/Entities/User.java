package tn.esprit.pidevcrashcode.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;

    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Role> roles = new ArrayList<>();

    @ManyToMany
    private Set<CampingCenter> visited = new HashSet<>();
    @ManyToMany
    private Set<CampingCenter> favoriteCamps = new HashSet<>();
    @ManyToMany
    private Set<Activity> favoriteActivities = new HashSet<>();

    private boolean isLoyal=false;

    private boolean archived = false;
    private boolean banned = false;
    @ManyToOne
    private Location location;

    @OneToMany(mappedBy = "user" )
    private List<Post> posts = new ArrayList<>();

    @ManyToMany(mappedBy = "users", cascade = CascadeType.REMOVE) //cascade remove fil manytomany matfasach ken l'association lentité tokeed @khalil
    private Set<Chatroom> chatrooms = new HashSet<>();

    @OneToMany(mappedBy = "user" , cascade = CascadeType.REMOVE)
    private Set<Review> reviews = new HashSet<>();

    @OneToOne(mappedBy = "user" , cascade = CascadeType.REMOVE)
    private Cart cart;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Notification> notifications = new ArrayList<>();


}
