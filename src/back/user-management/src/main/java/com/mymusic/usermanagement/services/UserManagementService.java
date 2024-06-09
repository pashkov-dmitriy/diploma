package com.mymusic.usermanagement.services;

import com.mymusic.usermanagement.dto.UpdateUserDto;
import com.mymusic.usermanagement.entities.User;
import com.mymusic.usermanagement.exceptions.UserAlreadyExistsException;
import com.mymusic.usermanagement.exceptions.UserNotFoundException;
import com.mymusic.usermanagement.repos.UserManagementRepository;
import lombok.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManagementService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserManagementRepository userManagementRepository;

    public UserManagementService(BCryptPasswordEncoder passwordEncoder, UserManagementRepository userManagementRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userManagementRepository = userManagementRepository;
    }

    public Optional<User> findUserById(@NonNull Long id) {
        return userManagementRepository.findById(id);
    }

    public Optional<User> findUserByUsername(@NonNull String username) {
        return userManagementRepository.findByUsername(username);
    }

    public Optional<User> findUserByEmail(@NonNull String email) {
        return userManagementRepository.findByEmail(email);
    }

    public boolean existsById(Long userId) {
        return userManagementRepository.existsById(userId);
    }

    public boolean existsByUsername(String username) {
        return userManagementRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userManagementRepository.existsByEmail(email);
    }

    public void deleteUser(Long userId) throws UserNotFoundException {
        if (existsById(userId)) {
            userManagementRepository.deleteById(userId);
        }
        else {
            throw new UserNotFoundException("User with id " + userId + "not found");
        }
    }

    public Optional<User> editUser(UpdateUserDto userDto, Long id) {
        return userManagementRepository.findById(id)
                    .map(user -> {
                        if (userDto.getUsername() != null) {
                            user.setUsername(userDto.getUsername());
                        }
                        if (userDto.getEmail() != null) {
                            user.setEmail(userDto.getEmail());
                        }
                        if (userDto.getDescription() != null) {
                            user.setDescription(userDto.getDescription());
                        }
                        if (userDto.getPassword() != null) {
                            var password = passwordEncoder.encode(userDto.getPassword());
                            user.setPasswordHash(password);
                        }
                        return userManagementRepository.save(user);
                    });
    }

    public User saveUser(@NonNull User user) throws UserAlreadyExistsException {
        if (!existsById(user.getId())) {
            return userManagementRepository.save(user);
        }
        else {
            throw new UserAlreadyExistsException("User with username" + user.getUsername() + "already exists");
        }
    }
}
