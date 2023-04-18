package tn.esprit.pidevcrashcode.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.Comment;
import tn.esprit.pidevcrashcode.Entities.Post;
import tn.esprit.pidevcrashcode.Entities.User;
import tn.esprit.pidevcrashcode.Repositories.CommentRepository;
import tn.esprit.pidevcrashcode.Repositories.PostRepository;
import tn.esprit.pidevcrashcode.Repositories.ReactionRepository;
import tn.esprit.pidevcrashcode.Repositories.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CommentService implements I_CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ReactionRepository reactionRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    public Comment CreateComment(Comment p)
    {
        return  commentRepository.save(p);
    }

    public void DeleteComment(int p)
    {
        commentRepository.deleteById(p);
    }
    public Comment UpdateComment(Comment p)
    {
        return  commentRepository.save(p);

    }
    public Comment RetriveComment(int  id)
    {
        return  commentRepository.findById(id).get();
    }
    public List<Comment> RetriveAllComments()
    {
        return  commentRepository.findAll();
    }

    public void AddCommentAndAssignToPostAndUser(Comment comment,int idUser,int idPost)
    {
        Post post=postRepository.findById(idPost).get();
        User user=userRepository.findById(idUser).get();
        comment.setUsername(user.getUsername());
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> SortCommentsByReactions(int idPost)
    {
        Post post=postRepository.findById(idPost).get();
        List<Comment> comments=commentRepository.findCommentsByPost(post);
        Map<Comment,Integer> CommentMap=new HashMap<>();

        for (Comment c:comments){
            CommentMap.put(c,reactionRepository.findAllByComment(c).size());
        }
        Map<Comment, Integer> sortedMap = CommentMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        List<Comment>SortedComments= new ArrayList<>(sortedMap.keySet());

        Collections.reverse(SortedComments);
        return SortedComments;
    }

    @Override
    public List<Comment> SortCommentsByDate(int idpost) {
        Post post=postRepository.findById(idpost).get();
        List<Comment> comments=commentRepository.findCommentsByPost(post);
        Map<Comment,Date> CommentMap=new HashMap<>();

        for (Comment c:comments){
            CommentMap.put(c,c.getDateComment());
        }
        Map<Comment, Date> sortedMap = CommentMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        List<Comment>SortedComments= new ArrayList<>(sortedMap.keySet());

        Collections.reverse(SortedComments);
        return SortedComments;
    }


}
