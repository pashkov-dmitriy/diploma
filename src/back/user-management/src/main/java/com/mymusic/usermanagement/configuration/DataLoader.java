package com.mymusic.usermanagement.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
@RequiredArgsConstructor
@Profile("development")
public class DataLoader implements CommandLineRunner {

    private final DataSource dataSource;


    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();

            // Загрузка данных в таблицу _users
            statement.executeUpdate("INSERT INTO _users (description, email, password_hash, profile_pic_uri, username) VALUES " +
                    "('User 1', 'user1@example.com', 'password1', 'http://example.com/user1.jpg', 'user1')," +
                    "('User 2', 'user2@example.com', 'password2', 'http://example.com/user2.jpg', 'user2')," +
                    "('User 3', 'user3@example.com', 'password3', 'http://example.com/user3.jpg', 'user3')," +
                    "(NULL, 'user5@example.com', '$2y$10$KLl.x8.RR36qPGD66RYOa.Wko.a/UklXNidcZrc9UH6q5IP6T3ZMa', NULL, 'user5');");
        }
    }
}
