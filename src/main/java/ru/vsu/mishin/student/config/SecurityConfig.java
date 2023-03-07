package ru.vsu.mishin.student.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.vsu.mishin.student.services.UserDetailsService;

/**
 * Класс конфигурации Spring Security для настройки аутентификации и авторизации пользователя.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Метод для настройки логики аутентификации пользователя.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * Метод для конфигурирования Spring Security и авторизации.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //---------------------------Токен для защиты от подделки запросов--------------------------------------
                .csrf().disable()
                //-------------------------------Настройка процесса авторизации-----------------------------------------
                .authorizeRequests() // процесс настройки авторизации
                .antMatchers("/main", "/auth/login", "/auth/registration", "/error").permitAll() // доступ для всех пользователей
                .anyRequest().authenticated()
//                .hasAnyRole("USER", "ADMIN") // для всех остальных запросов пользователь должен быть аутентифицирован => имеет назначенную роль
                //------------------------------------------------------------------------------------------------------
                .and()
                //------------------------------Настройка процесса аутентификации---------------------------------------
                .formLogin().loginPage("/auth/login") // добавление кастомной страницы логина path:auth/login
                .loginProcessingUrl("/process_login") // обработка данных со страницы логина path:auth/login (встроенный в Spring Security)
                .defaultSuccessUrl("/main", true) // редирект после успешной аутентификации
                .failureUrl("/auth/login?error") // редирект после проваленной аутентификации (по ключу error отображаются ошибки в представлении)
                //------------------------------------------------------------------------------------------------------
                .and()
                //-----------------------------------------Разлогин-----------------------------------------------------
                .logout() // удаление пользователя из сессии и очистка cookies пользователя
                .logoutUrl("/logout") // путь для разлогинивания
                .logoutSuccessUrl("/auth/login") // редирект после разлогина
        ;
    }

    /**
     * Метод для указания алгоритма шифрования пароля.
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
