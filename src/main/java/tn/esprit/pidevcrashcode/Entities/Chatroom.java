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
public class Chatroom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idChatroom;
    private String password;
    @ManyToMany
    private List<User> users = new ArrayList<>(); //khaleha list khater set mafichi order maaneha mataarafch chkoun owner
    @OneToMany(mappedBy = "chatroom",cascade = CascadeType.REMOVE)
    private Set<Message> messages = new HashSet<>();
}
