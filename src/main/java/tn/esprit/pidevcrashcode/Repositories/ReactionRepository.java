package tn.esprit.pidevcrashcode.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pidevcrashcode.Entities.Comment;
import tn.esprit.pidevcrashcode.Entities.Post;
import tn.esprit.pidevcrashcode.Entities.Reaction;


import java.util.List;


public interface ReactionRepository extends JpaRepository<Reaction,Integer> {

    List<Reaction> findAllByPost(Post post);
    List<Reaction> findAllByComment(Comment comment);
}
