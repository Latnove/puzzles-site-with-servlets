package service;

import entity.User;
import utils.PasswordUtil;
import utils.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class AuthorizationService {
    private final UserService userService;

    public AuthorizationService(UserService userService) {
        this.userService = userService;
    }

    public List<String> validateAndRegister(String login, String password, String email) {
        List<String> errors = new ArrayList<>();
        if (!ValidationUtil.validateLogin(login)) {
            errors.add("Введенный login некорректный: он должен быть длиной более 4 и содержать только буквы, цифры и символ _");
        }
        if (!ValidationUtil.validateEmail(email)) {
            errors.add("Введите корректную почту");
        }
        if (!ValidationUtil.validatePassword(password)) {
            errors.add("Введенный пароль слишком простой: он должен быть не менее 8 символов, иметь заглавную и прописную буквы");
        }
        if (userService.getByUsername(login) != null) {
            errors.add("Такой логин уже существует");
        }
        if (userService.getByEmail(email) != null) {
            errors.add("Эта почта уже используется");
        }

        if (errors.isEmpty()) {
            User user = new User(login, PasswordUtil.encrypt(password), email, "user");
            userService.save(user);
        }

        return errors;
    }

    public User authorize(String username, String password) {
        User user = userService.getByUsername(username);
        if (user == null || !PasswordUtil.encrypt(password).equals(user.getPasswordHash())) {
            return null;
        }

        return user;
    }
}
