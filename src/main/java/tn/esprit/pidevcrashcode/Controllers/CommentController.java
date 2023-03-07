package tn.esprit.pidevcrashcode.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.Comment;
import tn.esprit.pidevcrashcode.Services.I_CommentService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/commment")
public class CommentController {


    I_CommentService i_commentService;

    @PostMapping("/add-comment")
    public Comment addComment(@RequestBody Comment c){
        Comment comment=i_commentService.CreateComment(c);
        return comment;
    }
    @PostMapping("/update-comment")
    public Comment modifyComment(@RequestBody Comment p){
        Comment comment=i_commentService.UpdateComment(p);
        return comment;
    }
    @GetMapping("/retrieve-all-comments")
    public List<Comment> getComments()
    {
        List<Comment> L=i_commentService.RetriveAllComments();
        return L;
    }
    @GetMapping("/retrieve-comment/{comment-id}")
    public Comment getComment(@PathVariable("comment-id") int idPost)
    {
        Comment comment =i_commentService.RetriveComment(idPost);
        return comment;
    }

    @DeleteMapping("/delete-comment/{comment-id}")
    public void deleteComment(@PathVariable("comment-id") int idCommment)
    {
        i_commentService.DeleteComment(idCommment);
    }

    @PostMapping("/add-assing-comment/{user-id}/{post-id}")
    public void addAndAssignComment(@RequestBody Comment c,@PathVariable("post-id") int idUser,@PathVariable("post-id") int idPost){
            i_commentService.AddCommentAndAssignToPostAndUser(c,idUser,idPost);
    }
    @GetMapping (("/sort-comment-reaction/{post-id}"))
    public List<Comment> SortCommentsByReactions(@PathVariable ("post-id")int idPost){
        List<Comment> L= i_commentService.SortCommentsByReactions(idPost);
        return  L;
    }
    @GetMapping (("/sort-comment-date/{post-id}"))
    public List<Comment> SortCommentsByDate(@PathVariable ("post-id")int idPost){
        List<Comment> L= i_commentService.SortCommentsByDate(idPost);
        return  L;
    }



}
