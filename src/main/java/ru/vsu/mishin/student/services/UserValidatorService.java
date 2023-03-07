package ru.vsu.mishin.student.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.mishin.student.entity.User;
import ru.vsu.mishin.student.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserValidatorService {
    private final UserRepository userRepository;

    @Autowired
    public UserValidatorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
