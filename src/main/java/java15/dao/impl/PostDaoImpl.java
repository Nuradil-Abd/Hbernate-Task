package java15.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import java15.config.HibernateConfig;
import java15.dao.PostDao;
import java15.entities.Comment;
import java15.entities.Post;
import java15.entities.User;

import java.util.Collections;
import java.util.List;

public class PostDaoImpl implements PostDao {
    private final EntityManager em = HibernateConfig.getEntityManagerFactory().createEntityManager();
    @Override
    public void savePost(Long userId,Post post) {
        try{
            em.getTransaction().begin();
            User user = em.find(User.class, userId);
            if (user == null) {
                System.out.println("User with id " + userId + " not found");
                return;
            }
                if(user.getProfile() != null) {
                    em.persist(post);
                    post.setUser(user);
                    user.getPosts().add(post);
                    em.getTransaction().commit();
                    return;
                }
            System.out.println("saved post");
        }catch(Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            System.out.println("Error in saving post "+e.getMessage());
        }finally{
            em.close();
        }


    }

    @Override
    public List<Post> getPostByUserId(Long userId) {
        try{
            return em.createQuery("select p from Post p where p.user.id = : userId", Post.class)
                    .setParameter("userId",userId)
                    .getResultList();
        }catch(NoResultException e){
            return Collections.emptyList();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Post searchPost(String query) {
        try{
            return em.createQuery("select p from Post p where p.description iLike :query",Post.class)
                    .setParameter("query", "%" + query + "%")
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deletePostById(Long id) {
        EntityManager em = HibernateConfig.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = null;

        try{
            tx = em.getTransaction();
            tx.begin();
            Post post = em.find(Post.class, id);
            if(post != null){
                for(Comment comment : post.getComments()) {
                    comment.getUsers().forEach(u -> u.getComments().remove(comment));
                }
                em.remove(post);
                System.out.println("Post with id " + id + " deleted successfully");
            }else{
                System.out.println("Post with id " + id + " not found");
            }
            tx.commit();
        }catch(Exception e){
            if(tx != null && tx.isActive()){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            em.close();
        }

    }


}
