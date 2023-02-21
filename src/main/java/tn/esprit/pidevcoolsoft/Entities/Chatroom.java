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
public class Chatroom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idChatroom;
    private String members; // serialize(List) ---> deserialize(List)
    private String status;
    private String password;

    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "chatroom",cascade = CascadeType.REMOVE)
    private Set<Message> messages;
}
