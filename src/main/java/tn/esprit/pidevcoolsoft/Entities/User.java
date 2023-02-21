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
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(length = 1000)
    private String visited; //list of all visited camping centers serialized
    @Column(length = 1000)
    private String favoriteCamp; //list of all favorite camping centers serialized
    @Column(length = 1000)
    private String favoriteActivity; //list of favorite Activity serialized

    @ManyToOne
    private Location location;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.REMOVE)
    private Set<Post> posts;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.REMOVE)
    private Set<Chatroom> chatrooms;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.REMOVE)
    private Set<Message> messages;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.REMOVE)
    private Set<Review> reviews;

    @OneToOne(mappedBy = "user" , cascade = CascadeType.REMOVE)
    private Cart cart;



}
