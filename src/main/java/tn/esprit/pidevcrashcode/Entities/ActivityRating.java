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
public class ActivityRating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idActivityRating;
    private String username; //username jibou mel funcion getCurrentUser ;
    private int ratingValue; //1--> 5
    private Timestamp dateRate=new Timestamp((new Date()).getTime());
    @ManyToOne()
    private Activity activity;
    @PrePersist
    public void prePersist(){
        Calendar cal=Calendar.getInstance();
        cal.setTime(dateRate);
        cal.add(Calendar.HOUR_OF_DAY,1);
        dateRate=new Timestamp(cal.getTime().getTime());
    }
}
