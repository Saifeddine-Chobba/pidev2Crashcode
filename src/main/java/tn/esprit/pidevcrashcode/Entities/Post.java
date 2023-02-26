package tn.esprit.pidevcrashcode.Entities;

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
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPost;
    private String content;
    @Temporal(TemporalType.TIME)
    private Date datePost;
    private boolean forbiddenWords = false;
    private boolean archived = false ; //bch mayefsdouch il comments wel reactions
    @ManyToOne
    private User user;
    @ManyToOne
    private ForumSection forumSection;
}
