package tn.esprit.pidevcrashcode.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private int failedLoginAttempts;
    @Column(unique = true)

    private String email;
    private String password;
    public User(String username, String email, String password ) {
        this.username = username;
        this.email = email;
        this.password = password;

    }
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany
    private Set<CampingCenter> visited = new HashSet<>();
    @ManyToMany
    private Set<CampingCenter> favoriteCamps = new HashSet<>();
    @ManyToMany
    private Set<Activity> favoriteActivities = new HashSet<>();

    private boolean archived = false;
    private boolean banned = false;
    @ManyToOne
    private Location location;
    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Ban ban ;

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
    public Integer getId() {
        return idUser;
    }

    public void setId() {
        this.idUser = idUser;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }





}
