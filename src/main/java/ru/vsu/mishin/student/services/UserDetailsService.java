package ru.vsu.mishin.student.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vsu.mishin.student.entity.User;
import ru.vsu.mishin.student.repositories.UserRepository;
import ru.vsu.mishin.student.security.UserDetails;

import java.util.Optional;

/**
 * Класс для загрузки пользователя из БД.
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Метод для получения сущности пользователя.
     * @param username логин пользователя.
     * @throws UsernameNotFoundException если пользователь с таким username не найден.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден.");
        }
        return new UserDetails(user.get());
    }
}
