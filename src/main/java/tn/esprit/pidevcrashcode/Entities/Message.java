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
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMessage;
    private String text;
    @Temporal(TemporalType.TIME)   //chouf l cours fih l format mtaa TIME
    private Date date;

    @ManyToOne
    private User user;
    @ManyToOne
    private Chatroom chatroom;
}
