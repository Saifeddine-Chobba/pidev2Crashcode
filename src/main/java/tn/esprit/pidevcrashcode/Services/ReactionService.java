package tn.esprit.pidevcrashcode.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.Comment;
import tn.esprit.pidevcrashcode.Entities.Post;
import tn.esprit.pidevcrashcode.Entities.Reaction;
import tn.esprit.pidevcrashcode.Repositories.CommentRepository;
import tn.esprit.pidevcrashcode.Repositories.PostRepository;
import tn.esprit.pidevcrashcode.Repositories.ReactionRepository;
import tn.esprit.pidevcrashcode.Repositories.UserRepository;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ReactionService implements I_ReactionService {
    @Autowired
    ReactionRepository reactionRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    public Reaction CreateReaction(Reaction reaction)
    {
        return  reactionRepository.save(reaction);
    }
    public void DeleteReaction(int id)
    {
        reactionRepository.deleteById(id);
    }
    public Reaction UpdateReaction(Reaction reaction)
    {
        return  reactionRepository.save(reaction);
    }
    public Reaction RetriveReaction(int  id)

    {
        return  reactionRepository.findById(id).get();
    }
    public List<Reaction> RetriveAllReactions()
    {
        return  reactionRepository.findAll();
    }
    public void AddReactionAndAssignToPost(Reaction reaction,int idUSer ,int idPost){

        Post post=postRepository.findById(idPost).get();
        reaction.setUser(userRepository.findById(idUSer).get());

        reaction.setPost(post);
        reactionRepository.save(reaction);
    }
    public void AddReactionAndAssignToComment(Reaction reaction,int idUSer, int idComment){
        Comment comment=commentRepository.findById(idComment).get();
        reaction.setUser(userRepository.findById(idUSer).get());
        reaction.setComment(comment);
        reactionRepository.save(reaction);
    }


}




