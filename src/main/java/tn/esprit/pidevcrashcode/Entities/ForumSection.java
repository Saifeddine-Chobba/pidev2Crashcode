package tn.esprit.pidevcrashcode.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForumSection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idForumSection;
    private String name;
    private boolean archived ; // lezem tdour ala les posts lkol mta3 section trodhom archivé (query + for loop)
    @OneToMany(mappedBy = "forumSection")
    private Set<Post> posts = new HashSet<>();
}
