package java15.service;

import java15.entities.Profile;
import java15.entities.User;

public interface UserService {
    void saveUser(User user);
    User findUserById(Long id);
    void updateUserProfile(Long userId, Profile profile);
    void deleteUser(Long id);
}
