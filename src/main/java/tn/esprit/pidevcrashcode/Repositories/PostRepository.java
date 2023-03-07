package tn.esprit.pidevcrashcode.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.pidevcrashcode.Entities.ForumSection;
import tn.esprit.pidevcrashcode.Entities.Post;
import tn.esprit.pidevcrashcode.Entities.User;

import java.util.List;
public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findAllByForumSection(ForumSection section);
    List<Post> findAllByUser(User user);
    @Query("SELECT p FROM Post p WHERE YEAR(p.datePost) = :year")
    List<Post> findAllByDatePost_Year(@Param("year")int y);

}
