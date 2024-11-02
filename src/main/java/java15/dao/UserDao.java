package java15.dao;

import java15.entities.Profile;
import java15.entities.User;

public interface UserDao {
    void saveUser(User user);
    User findUserById(Long id);
    User findUserByEmail(String email);
    void updateUserProfile(Long userId, Profile profile);
    void deleteUser(Long id);

}
