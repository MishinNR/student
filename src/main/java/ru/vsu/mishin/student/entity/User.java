package ru.vsu.mishin.student.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Класс для описания сущности: application_user
 */
@Entity
@Table(name = "application_user")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    @Size(min = 2, max = 20, message = "Логин должен быть от 2 до 100 символов.")
    private String username;

    @Column(name = "password")
    @Size(min = 5, message = "Пароль должен состоять минимум из 5 символов.")
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
