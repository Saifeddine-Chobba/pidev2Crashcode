package tn.esprit.pidevcrashcode.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReaction;
    @Enumerated(EnumType.STRING)
    private ReactionType reactionType;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String username;
    @ManyToOne()
    private Post post; // hotha null ken reaction saret ala comment
    @ManyToOne()
    private Comment comment;
    @ManyToOne
    private User user;
}
