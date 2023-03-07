package ru.vsu.mishin.student.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.vsu.mishin.student.entity.User;
import ru.vsu.mishin.student.services.UserValidatorService;

/**
 * Класс для валидации данных при регистрации пользователя.
 */
@Component
public class UserValidator implements Validator {
    private final UserValidatorService userValidatorService;

    @Autowired
    public UserValidator(UserValidatorService userValidatorService) {
        this.userValidatorService = userValidatorService;
    }

    /**
     * Метод для проверки принадлежности к классу {@link User}
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (userValidatorService.loadUserByUsername(user.getUsername()).isPresent()) {
            errors.rejectValue("username", "", "Пользователь с таким логином уже существует.");
        }
    }
}
