package java15.config;

import jakarta.persistence.EntityManagerFactory;
import java15.entities.Comment;
import java15.entities.Post;
import java15.entities.Profile;
import java15.entities.User;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateConfig {
    private static EntityManagerFactory emf;
    public static EntityManagerFactory getEntityManagerFactory() {
        if(emf == null) {
            try {
                Properties properties = new Properties();
                properties.put(Environment.JAKARTA_JDBC_URL, "jdbc:postgresql://localhost:5432/hb_task");
                properties.put(Environment.JAKARTA_JDBC_USER, "postgres");
                properties.put(Environment.JAKARTA_JDBC_PASSWORD, "1234");
                properties.put(Environment.HBM2DDL_AUTO, "update");
                properties.put(Environment.SHOW_SQL, "true");

                Configuration configuration = new Configuration();
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Post.class);
                configuration.addAnnotatedClass(Comment.class);
                configuration.addAnnotatedClass(Profile.class);

                emf = configuration.buildSessionFactory();
            } catch (HibernateException e) {
                System.err.println("Initial EntityManagerFactory creation failed." + e.getMessage());
                throw new ExceptionInInitializerError(e);
            }
        }
        return emf;
    }

    public static void close(){
        if(emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
