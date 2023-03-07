package tn.esprit.pidevcrashcode.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPost;
    private String content;
    private Timestamp datePost=new Timestamp((new Date()).getTime());
    private boolean forbiddenWords = false;
    private boolean archived = false ;
    @JsonIgnore
    @ManyToOne
    private User user;
    @ManyToOne
    private ForumSection forumSection;
    @PrePersist
    private void prePersist() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(datePost);
        cal.add(Calendar.HOUR_OF_DAY, 1);
        datePost = new Timestamp(cal.getTime().getTime());
    }
}
