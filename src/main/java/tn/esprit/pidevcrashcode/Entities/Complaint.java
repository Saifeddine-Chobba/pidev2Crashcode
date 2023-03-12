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
public class Complaint implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComplaint;
    @Enumerated(EnumType.STRING)
    private TypeComplaint typeComplaint;
    private String username;  //estaaml getCurrentUser() bch tjib user li ada complaint mawjouda fi UserService
    private Timestamp dateComplaint=new Timestamp((new Date()).getTime());
    @Column( columnDefinition = "TEXT")
    private String description;
    @Column( columnDefinition = "TEXT")
    private String response;
    @Enumerated(EnumType.STRING)
    private Status status = Status.Ongoing;
    @Column(nullable = true)
    private String imageName;  // esm image li yhabatha l user bil upload
    private String sentiment;


    @PrePersist
    public void prePersist(){
        Calendar cal=Calendar.getInstance();
        cal.setTime(dateComplaint);
        cal.add(Calendar.HOUR_OF_DAY,1);
        dateComplaint=new Timestamp(cal.getTime().getTime());
    }
}
