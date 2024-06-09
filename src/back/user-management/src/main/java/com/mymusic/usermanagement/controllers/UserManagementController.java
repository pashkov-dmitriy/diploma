package com.mymusic.usermanagement.controllers;

import com.mymusic.usermanagement.controllers.responses.AvailabilityResponse;
import com.mymusic.usermanagement.controllers.responses.BooleanResponse;
import com.mymusic.usermanagement.dto.PublicUserDto;
import com.mymusic.usermanagement.dto.UpdateUserDto;
import com.mymusic.usermanagement.dto.UserRegisterRequestDto;
import com.mymusic.usermanagement.entities.User;
import com.mymusic.usermanagement.exceptions.UserAlreadyExistsException;
import com.mymusic.usermanagement.exceptions.UserNotFoundException;
import com.mymusic.usermanagement.mappers.UserMapper;
import com.mymusic.usermanagement.services.UserManagementService;
import com.mymusic.usermanagement.utils.HeaderStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UserManagementController {

    private final HeaderStorage headerStorage;
    private final UserManagementService userManagementService;

    @GetMapping("/users")
    public ResponseEntity<User> getUserInfo() {
        Long id = Long.valueOf(HeaderStorage.getMyHeader());

        return userManagementService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/users/{username}", produces = "application/json")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        return userManagementService.findUserByUsername(username)
                .map(user -> ResponseEntity.ok()
                        .header("Content-Type", "application/json")
                        .body(user))
                .orElse(ResponseEntity.notFound().build());
    }

//    @GetMapping(path = "/users")
//    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
//        return userManagementService.findUserByEmail(email)
//                .map(user -> ResponseEntity.ok()
//                        .header("Content-Type", "appliction/json")
//                        .body(user))
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<PublicUserDto> getUserById(@PathVariable("id") Long id) {
        return userManagementService.findUserById(id)
                .map(user -> ResponseEntity.ok(UserMapper.INSTANCE.toPublicUserDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/register", produces = "application/json")
    public ResponseEntity<?> createUser(@RequestBody final UserRegisterRequestDto user) {
        try {
            User newUser = new User();

            newUser.setUsername(user.getUsername());
            newUser.setEmail(user.getEmail());

            User savedUser = userManagementService.saveUser(newUser);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        }
        catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserDto user) {
        try {
            Long id = Long.valueOf(HeaderStorage.getMyHeader());
            return userManagementService.editUser(user, id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userManagementService.deleteUser(id);
            return ResponseEntity.ok().build();
        }
        catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
