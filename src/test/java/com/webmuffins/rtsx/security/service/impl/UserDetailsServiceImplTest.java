package com.webmuffins.rtsx.security.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import com.webmuffins.rtsx.security.constant.Role;
import com.webmuffins.rtsx.security.dto.user.UserData;
import com.webmuffins.rtsx.security.entity.User;
import com.webmuffins.rtsx.security.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    private static final String DEFAULT_USERNAME = "email";

    private User user;
    private UserData userData;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserDetailsServiceImpl testInstance;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setRole(Role.SYSTEM_ADMIN);
        user.setEmail(DEFAULT_USERNAME);

        userData = new UserData();
        userData.setUsername(DEFAULT_USERNAME);
        userData.setAuthorities(Role.SYSTEM_ADMIN.getAuthorities());
    }

    @Test
    void shouldLoadByUsername() {
        when(userService.findUserByEmail(DEFAULT_USERNAME)).thenReturn(user);

        UserDetails actual = testInstance.loadUserByUsername(DEFAULT_USERNAME);

        assertThat(actual)
                .isNotNull()
                .isEqualTo(userData);
    }
}
