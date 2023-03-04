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
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNotification;
    private String content;
    private boolean notificationHigh=false;

    private Timestamp dateNotification=new Timestamp((new Date()).getTime());
    private boolean viewed = false;  // ken thell notification twali maktouba bel gris kima facebook (HACHTEK BEHA BAAD FIL FRONT)
    @ManyToOne
    private User user;
    @PrePersist
    public void prePersist(){
        Calendar cal=Calendar.getInstance();
        cal.setTime(dateNotification);
        cal.add(Calendar.HOUR_OF_DAY,1);
        dateNotification=new Timestamp(cal.getTime().getTime());
    }
}
