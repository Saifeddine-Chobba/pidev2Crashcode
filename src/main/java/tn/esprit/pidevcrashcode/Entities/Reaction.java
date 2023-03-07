package tn.esprit.pidevcrashcode.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
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
    private Timestamp dateReaction=new Timestamp((new Date()).getTime());
    private String username;
    @ManyToOne()
    private Post post;
    @ManyToOne()
    private Comment comment;
    @ManyToOne
    private User user;

    @PrePersist
    private void prePersist() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateReaction);
        cal.add(Calendar.HOUR_OF_DAY, 1);
        dateReaction = new Timestamp(cal.getTime().getTime());
    }
}
