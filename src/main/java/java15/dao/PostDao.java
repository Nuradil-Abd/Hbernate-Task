package java15.dao;

import jakarta.persistence.EntityManager;
import java15.entities.Post;

import java.util.List;

public interface PostDao {
    void savePost(Long userId,Post post);
    List<Post> getPostByUserId(Long userId);
    Post searchPost (String query);
    void deletePostById(Long id);
}
