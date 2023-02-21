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
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComment;
    private String username;  //display as "deleted" in case the user is deleted
    @Column(length = 1000)
    private String content;
    private String imageName;
    @Temporal(TemporalType.TIME)
    private Date commentDate;
    private boolean forbiddenWords;

    @ManyToOne()
    private Post post; //kif tfasakh post hethia twali NULL fil base ken bch testaamalha aaml condition bch mayaatikch NullPointerException wela chesmha
}
