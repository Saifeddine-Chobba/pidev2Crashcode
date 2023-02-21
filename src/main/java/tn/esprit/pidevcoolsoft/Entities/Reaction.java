package tn.esprit.pidevcoolsoft.Entities;

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
    private Post post; //kif tfasakh post hethia twali NULL fil base ken bch testaamalha aaml condition bch mayaatikch NullPointerException wela chesmha
    @ManyToOne()
    private Comment comment; //kif tfasakh comment hethia twali NULL fil base ken bch testaamalha aaml condition bch mayaatikch NullPointerException wela chesmha

    @ManyToOne
    private User user;
}
