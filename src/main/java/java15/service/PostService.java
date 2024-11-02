package java15.service;

import jakarta.persistence.EntityManager;
import java15.entities.Post;

import java.util.List;

public interface PostService {
    void savePost(Post post);
   List<Post> getPostByUserId(Long userId);
    Post searchPost (String query);
    void deletePostById(Long id);
}
