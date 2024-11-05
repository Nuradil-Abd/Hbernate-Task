package java15.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import java15.config.HibernateConfig;
import java15.dao.UserDao;
import java15.entities.Profile;
import java15.entities.User;

public class UserDaoImpl implements UserDao {

    private final EntityManager em = HibernateConfig.getEntityManagerFactory().createEntityManager();


    @Override
    public void saveUser(User user) {
        em.getTransaction().begin();
        try {
            if (findUserByEmail(user.getEmail()) != null) {
                System.out.println("User with email " + user.getEmail() + " already exists.");
            } else {
                em.persist(user);
                em.getTransaction().commit();
                System.out.println("User saved successfully");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error in saving user: " + e.getMessage());
        }
    }

    @Override
    public User findUserById(Long id) {
        try{
        return em.find(User.class, id);
        }finally{
            em.close();
        }
    }
    @Override
    public User findUserByEmail(String email) {
        try {
            return em.createQuery("select u from User u where u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void deleteUser(Long id) {
        EntityManager em = HibernateConfig.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = null;

        try{
            tx = em.getTransaction();
            tx.begin();

            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
                System.out.println("User with id " + id + " has been deleted.");
            }else{
                System.out.println("User with id " + id + " does not exist.");
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

    @Override
    public void updateUserProfile(Long userId, Profile profile) {
    EntityTransaction tx = em.getTransaction();
    try{
        tx.begin();

        User user = em.find(User.class, userId);
        if( user == null){
            throw new IllegalArgumentException("User with id " + userId + " does not exist.");
        }

        Profile existingProfile = user.getProfile();
        if(existingProfile != null){
            existingProfile.setFullName(profile.getFullName());
            existingProfile.setBio(profile.getBio());
            existingProfile.setDateOfBirth(profile.getDateOfBirth());
            existingProfile.setGender(profile.getGender());
            em.merge(existingProfile);
        }else{
            profile.setId(userId);
            profile.setUser(user);
            user.setProfile(profile);
            em.persist(profile);
        }
        tx.commit();
        System.out.println("User profile updated successfully");
    }catch(Exception e){
        if(tx.isActive()){
            tx.rollback();
        }
        e.printStackTrace();
    }
    }
}
