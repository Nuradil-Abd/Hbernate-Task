package java15.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java15.config.HibernateConfig;
import java15.dao.CommentDao;
import java15.entities.Comment;
import java15.entities.Post;

import java.util.Collections;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private final EntityManager em = HibernateConfig.getEntityManagerFactory().createEntityManager();

    @Override
    public void saveComment(Long postId, Comment comment) {

        if (postId == null) {
            throw new IllegalArgumentException("Post ID cannot be null.");
        }

        try {
            em.getTransaction().begin();

            Post post = em.find(Post.class, postId);
            if (post == null) {
                throw new IllegalArgumentException("Post with ID " + postId + " not found.");
            }
            comment.setPost(post);
            em.persist(comment);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Comment> findCommentByPostId(Long postId) {
       try{
           return em.createQuery("select c from Comment c where c.post.id = : postId", Comment.class)
                   .setParameter("postId",postId).getResultList();
       }catch (Exception e){
           e.printStackTrace();
           return Collections.emptyList();
       }
    }

    @Override
    public void updateComment(Long commentId, String newText) {
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            Comment comment = em.find(Comment.class, commentId);
            if (comment == null) {
                throw new IllegalArgumentException("Comment with ID " + commentId + " not found.");
            }

            comment.setText(newText);
            tx.commit();
            System.out.println("updated comment with ID " + commentId + " to " + newText);
        }catch (Exception e){
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteComment(Long commentId) {
        EntityManager em = HibernateConfig.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx =em.getTransaction();
            tx.begin();

            Comment comment = em.find(Comment.class, commentId);
            if (comment != null) {
                em.remove(comment);
                System.out.println("Comment with ID " + commentId + " deleted.");
            }else{
                System.out.println("Comment with ID " + commentId + " not found.");
            }
            tx.commit();
        }catch (Exception e){
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
