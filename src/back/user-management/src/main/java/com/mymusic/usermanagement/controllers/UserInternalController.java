package com.mymusic.usermanagement.controllers;

import com.mymusic.usermanagement.controllers.responses.AvailabilityResponse;
import com.mymusic.usermanagement.controllers.responses.BooleanResponse;
import com.mymusic.usermanagement.dto.AuthUserDto;
import com.mymusic.usermanagement.dto.PublicUserDto;
import com.mymusic.usermanagement.dto.UserPreviewDto;
import com.mymusic.usermanagement.entities.User;
import com.mymusic.usermanagement.mappers.UserMapper;
import com.mymusic.usermanagement.services.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/users")
public class UserInternalController {

    private final UserManagementService userManagementService;

    @GetMapping()
    public ResponseEntity<AuthUserDto> getAuthCredits(@RequestParam("email") String email) {
        return userManagementService.findUserByEmail(email)
                .map(user -> ResponseEntity.ok(UserMapper.INSTANCE.toAuthUserDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{id}/preview")
    public ResponseEntity<UserPreviewDto> getUserById(@PathVariable("id") Long id) {
        return userManagementService.findUserById(id)
                .map(user -> ResponseEntity.ok(UserMapper.INSTANCE.toUserPreviewDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "{id}/exists", produces = "application/json")
    public ResponseEntity<BooleanResponse> checkExists(@PathVariable("id") Long id) {
        return ResponseEntity.ok()
                .body(new BooleanResponse(userManagementService.existsById(id)));
    }

    @GetMapping(path = "check-exists", produces = "application/json")
    public ResponseEntity<AvailabilityResponse> checkExists(
            @RequestParam String username,
            @RequestParam String email
    ) {
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(new AvailabilityResponse(
                        userManagementService.existsByUsername(username),
                        userManagementService.existsByEmail(email)
                ));
    }
}
