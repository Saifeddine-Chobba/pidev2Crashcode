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
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNotification;
    private String content;
    @Temporal(TemporalType.TIME)
    private Date date;
    private boolean viewed = false;  // ken thell notification twali maktouba bel gris kima facebook (HACHTEK BEHA BAAD FIL FRONT)
    @ManyToOne
    private User user;
}
