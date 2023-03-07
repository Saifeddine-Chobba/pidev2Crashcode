package tn.esprit.pidevcrashcode.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pidevcrashcode.Entities.Comment;
import tn.esprit.pidevcrashcode.Entities.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> findCommentsByPost(Post post);
}
