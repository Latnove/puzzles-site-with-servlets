package service;

import com.cloudinary.Cloudinary;
import com.zaxxer.hikari.HikariDataSource;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import dto.UserDto;
import entity.User;
import utils.PasswordUtil;
import utils.ValidationUtil;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDao userDao;
    private final Cloudinary cloudinary;

    public UserService(HikariDataSource dataSource, Cloudinary cloudinary) {
        this.userDao = new UserDaoImpl(dataSource);
        this.cloudinary = cloudinary;
    }

    public void save(User user) {
        userDao.save(user);
    }

    public User getByUsername(String username) {
        Optional<User> wrap = userDao.getByUsername(username);
        return wrap.orElse(null);
    }

    public User getByEmail(String email) {
        Optional<User> wrap = userDao.getByEmail(email);
        return wrap.orElse(null);

    }

    public UserDto getById(int id) {
        User user = userDao.getById(id);
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getImage(), user.getRole(), user.getCreatedAt());
    }

    public List<String> changePassword(String username, String oldPassword, String newPassword) {
        List<String> errors = new ArrayList<>();
        if (!getByUsername(username).getPasswordHash().equals(PasswordUtil.encrypt(oldPassword))) {
            errors.add("Старый пароль указан неверно");
        }
        if (!ValidationUtil.validatePassword(newPassword)) {
            errors.add("Введенный пароль слишком простой: он должен быть не менее 8 символов, иметь заглавную и прописную буквы");
        }

        if (errors.isEmpty()) {
            userDao.changePassword(username, PasswordUtil.encrypt(newPassword));
        }

        return errors;
    }

    public String changeAvatar(String username, Part part)  throws IOException {
        int available = part.getInputStream().available();
        byte[] bytes = new byte[available];
        part.getInputStream().read(bytes);
        String newUrlImage = (String) cloudinary.uploader().upload(bytes, new HashMap<>()).get("secure_url");
        userDao.changeAvatar(username, newUrlImage);

        return newUrlImage;
    }
}
