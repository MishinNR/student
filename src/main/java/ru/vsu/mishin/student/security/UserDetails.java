package ru.vsu.mishin.student.security;

import org.springframework.security.core.GrantedAuthority;
import ru.vsu.mishin.student.entity.User;

import java.util.Collection;

/**
 * Класс обертка для обработки при аутентификации.
 */
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private final User user;

    public UserDetails(User user) {
        this.user = user;
    }

    /**
     * Метод для получения роли пользователя и авторизация его действий.
     * @return список прав пользователя
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * Метод для получения пароля пользователя.
     * @return пароль пользователя
     */
    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    /**
     * Метод для получения логина пользователя.
     * @return логин пользователя
     */
    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    /**
     * Метод для получения информации: истек ли срок действия учетной записи.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Метод для получения информации: заблокирована ли учетная запись.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Метод для получения информации: истек ли срок действия пароля.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Метод для получения информации: работает ли учетная запись.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
