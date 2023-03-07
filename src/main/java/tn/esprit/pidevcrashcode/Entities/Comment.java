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
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComment;
    private String username;  //display as "deleted" in case the user is deleted
    @Column(length = 1000)
    private String content;
    private String imageName;
    private Timestamp dateComment=new Timestamp((new Date()).getTime());
    private boolean forbiddenWords = false ;
    @ManyToOne()
    private Post post;
    @PrePersist
    private void prePersist() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateComment);
        cal.add(Calendar.HOUR_OF_DAY, 1);
        dateComment = new Timestamp(cal.getTime().getTime());
    }

}
