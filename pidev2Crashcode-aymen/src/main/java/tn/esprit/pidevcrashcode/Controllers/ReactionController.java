package tn.esprit.pidevcrashcode.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.Reaction;
import tn.esprit.pidevcrashcode.Services.I_ReactionService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reaction")
public class ReactionController {

    I_ReactionService i_reactionService;
    @PostMapping("/add-reaction")
    public Reaction addReacrtion(@RequestBody Reaction r){
        Reaction reaction=i_reactionService.CreateReaction(r);
        return reaction;
    }
    @PostMapping("/update-reaction")
    public Reaction modifyReaction(@RequestBody  Reaction r){
        Reaction reaction=i_reactionService.UpdateReaction(r);
        return reaction;
    }
    @GetMapping("/retrieve-all-reactions")
    public List<Reaction> getReactions()
    {
        List<Reaction> L=i_reactionService.RetriveAllReactions();
        return L;
    }
    @GetMapping("/retrieve-reaction/{reaction-id}")
    public Reaction getReaction(@PathVariable("reaction-id") int idReact)
    {
        Reaction reaction =i_reactionService.RetriveReaction(idReact);
        return reaction;
    }
    @DeleteMapping("/delete-reaction/{reaction-id}")
    public void deleteReaction(@PathVariable("reaction-id") int idReact)
    {
        i_reactionService.DeleteReaction(idReact);
    }
    @PostMapping("/add-assing-reaction-topost/{user-id}/{post-id}")
    public void addAndAssignReactionPost(@RequestBody Reaction c,@PathVariable("user-id") int idUser, @PathVariable("post-id") int idPost){
        i_reactionService.AddReactionAndAssignToPost(c,idUser,idPost);
    }
    @PostMapping("/add-assing-reaction-tocomment/{user-id}/{comment-id}")
    public void addAndAssignReactionComment(@RequestBody Reaction c,@PathVariable("user-id") int idUser, @PathVariable("comment-id") int idComm){
        i_reactionService.AddReactionAndAssignToComment(c,idUser,idComm);
    }
}
