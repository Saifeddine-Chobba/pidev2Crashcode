package tn.esprit.pidevcrashcode.Services;

import tn.esprit.pidevcrashcode.Entities.Reaction;

import java.util.List;

public interface I_ReactionService {
    Reaction CreateReaction(Reaction reaction);
    void DeleteReaction(int  idReaction);
    Reaction UpdateReaction(Reaction reaction);
    Reaction RetriveReaction(int  id);

    List<Reaction> RetriveAllReactions();

    void AddReactionAndAssignToPost(Reaction reaction,int idUSer, int idPost);
    void AddReactionAndAssignToComment(Reaction reaction,int idUser, int idComment);



}
