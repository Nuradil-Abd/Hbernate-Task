package java15.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java15.config.HibernateConfig;
import java15.dao.ProfileDao;
import java15.entities.Profile;
import java15.entities.User;

public class ProfileDaoImpl implements ProfileDao {
    private final EntityManager em = HibernateConfig.getEntityManagerFactory().createEntityManager();

    @Override
    public void saveProfile(Long userId, Profile profile) {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            User user = em.find(User.class, userId);
            if (user == null) {
                System.out.println("User with id " + userId + " not found");
                return;
            }
            profile.setId(userId);
            user.setProfile(profile);
            em.persist(profile);
            em.merge(user);
            tx.commit();
            System.out.println("saved profile " + profile);
        } catch (Exception e) {
            if (tx != null && em.getTransaction().isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Profile findProfileById(Long userId) {
        try {
            User user = em.find(User.class, userId);
            if (user != null) {
                return user.getProfile();
            } else {
                System.out.println("User with id " + userId + " not found");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteProfileByUserId(Long userId) {
        EntityManager em = HibernateConfig.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            User user = em.find(User.class, userId);
            if (user != null) {
                Profile profile = user.getProfile();
                if (profile != null) {
                    em.remove(profile);
                    user.setProfile(null);
                    System.out.println("Profile for user Id " + userId + " deleted successfully");
                } else {
                    System.out.println("No profile found for user Id " + userId);
                }
            } else {
                System.out.println("User with id " + userId + " not found");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

    }
}
