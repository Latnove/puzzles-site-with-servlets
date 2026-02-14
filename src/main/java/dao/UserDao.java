package dao;

import entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void save(User user);
    void changePassword(String username, String password);
    void changeAvatar(String username, String imagePath);

    Optional<User> getByUsername(String login);

    Optional<User> getByEmail(String email);

    User getById(Integer id);
}
