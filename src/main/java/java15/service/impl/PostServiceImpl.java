package java15.service.impl;

import jakarta.persistence.EntityManager;
import java15.dao.PostDao;
import java15.dao.impl.PostDaoImpl;
import java15.entities.Post;
import java15.service.PostService;

import java.util.List;

public class PostServiceImpl implements PostService {
    private final PostDao postDao = new PostDaoImpl();

    @Override
    public void savePost(Long userId,Post post) {
        postDao.savePost(userId, post);
    }

    @Override
    public List<Post> getPostByUserId(Long userId) {
        return postDao.getPostByUserId(userId);
    }

    @Override
    public Post searchPost(String query) {
        return postDao.searchPost(query);
    }

    @Override
    public void deletePostById(Long id) {
        postDao.deletePostById(id);
    }

}
