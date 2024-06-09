-- Вставка роли "USER", если она еще не существует
INSERT INTO USER_MANAGEMENT.ROLES (name)
VALUES ('USER'),
       ('ADMIN');

-- Вставка пяти пользователей
INSERT INTO USER_MANAGEMENT._USERS (username, password_hash, description, profile_pic_uri)
VALUES
    ('user1', 'hash1', 'Description for userDetailsImpl 1', 'http://example.com/profile1.jpg'),
    ('user2', 'hash2', 'Description for userDetailsImpl 2', 'http://example.com/profile2.jpg'),
    ('user3', 'hash3', 'Description for userDetailsImpl 3', 'http://example.com/profile3.jpg'),
    ('user4', 'hash4', 'Description for userDetailsImpl 4', 'http://example.com/profile4.jpg'),
    ('user5', 'hash5', 'Description for userDetailsImpl 5', 'http://example.com/profile5.jpg');

INSERT INTO USER_MANAGEMENT.USER_ROLES (user_id, role_id)
VALUES (1, 1), (2, 1), (3, 1), (4, 1), (5, 1);