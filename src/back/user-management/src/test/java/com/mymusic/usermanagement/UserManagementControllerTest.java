package com.mymusic.usermanagement;

import com.mymusic.usermanagement.controllers.UserManagementController;
import com.mymusic.usermanagement.entities.User;
import com.mymusic.usermanagement.services.UserManagementService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest
public class UserManagementControllerTest {

    @MockBean
    UserManagementService userManagementService;

    @Autowired
    UserManagementController userManagementController;

}
