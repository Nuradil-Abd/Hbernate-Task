package java15.service;

import java15.dao.UserDao;
import java15.entities.Profile;
import java15.entities.User;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public void saveUser(User user) {

        if (userDao.findUserByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        validateUser(user);
        userDao.saveUser(user);
    }

    public User findUserById(Long id) {
        User user = userDao.findUserById(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found.");
        }
        return user;
    }

    @Override
    public void updateUserProfile(Long userId, Profile profile) {
        userDao.updateUserProfile(userId, profile);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    private void validateUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (user.getEmail() == null || !user.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long.");
        }
    }
}