package com.webmuffins.rtsx.security.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.webmuffins.rtsx.security.entity.User;
import com.webmuffins.rtsx.security.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final String DEFAULT_USER_EMAIL = "email@email.em";

    private User user;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl testInstance;

    @BeforeEach
    void setUp() {
        user = new User();

        user.setEmail(DEFAULT_USER_EMAIL);
    }

    @Test
    void shouldFindUserByEmail() {
        when(userRepository.findByEmail(DEFAULT_USER_EMAIL)).thenReturn(Optional.of(user));

        User actual = testInstance.findUserByEmail(DEFAULT_USER_EMAIL);

        assertThat(actual)
                .isNotNull()
                .isEqualTo(user);
    }
}
