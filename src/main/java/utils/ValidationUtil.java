package utils;

import java.util.regex.Pattern;

public class ValidationUtil {

    public static boolean validateLogin(String str) {
        if (str == null) {
            return false;
        }
        String regex = "^[a-zA-Z][a-zA-Z0-9_]{4,}$";
        return Pattern.matches(regex, str);
    }

    public static boolean validatePassword(String str) {
        if (str == null) {
            return false;
        }
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,}$";
        return Pattern.matches(regex, str);
    }

    public static boolean validateEmail(String str) {
        if (str == null) {
            return false;
        }
        String regex = "^[\\w.-]+@[\\w-]+\\.[a-z]{2,6}$";
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(str).matches();
    }
}