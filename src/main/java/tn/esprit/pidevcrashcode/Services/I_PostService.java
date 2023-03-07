package tn.esprit.pidevcrashcode.Services;

import tn.esprit.pidevcrashcode.Entities.Post;

import java.util.List;
import java.util.Map;

public interface I_PostService {
    Post CreatePost(Post p);
    void DeletePost(int  idpost);
    Post UpdatePost(Post p);
    Post RetrivePost(int  id);
    List <Post> RetriveAllPosts();

    void AddPostAndAssignToUser(Post post,int idUser,int idSection);

    List<Post> SortPostsByReacts(int idsection);

    List<Post> SortPostsByDate(int idsection);

    int NumberOfPostsByUser(int idUser);

    Map<Integer,List<Post>> PostsPerMonthOfYear(int year);


    List<Post> PostsOfyear(int year);






}
