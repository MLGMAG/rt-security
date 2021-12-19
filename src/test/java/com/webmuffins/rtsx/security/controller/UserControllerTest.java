package com.webmuffins.rtsx.security.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.webmuffins.rtsx.security.constant.Role;
import com.webmuffins.rtsx.security.dto.user.AuthenticationRequestDto;
import com.webmuffins.rtsx.security.dto.user.AuthenticationResponseDto;
import com.webmuffins.rtsx.security.entity.User;
import com.webmuffins.rtsx.security.security.JwtTokenProvider;
import com.webmuffins.rtsx.security.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private static final String DEFAULT_EMAIL = "email@email.me";
    private static final String DEFAULT_PASSWORD = "password";
    private static final String DEFAULT_TOKEN = "token";
    private static final Role DEFAULT_ROLE = Role.USER;

    private AuthenticationRequestDto authenticationRequestDto;
    private User user;
    private AuthenticationResponseDto authenticationResponseDto;
    private UsernamePasswordAuthenticationToken authentication;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserService userService;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private AuthenticationController testInstance;

    @BeforeEach
    void setUp() {
        authenticationRequestDto = new AuthenticationRequestDto();
        authenticationResponseDto = new AuthenticationResponseDto();
        user = new User();
        authentication = new UsernamePasswordAuthenticationToken(DEFAULT_EMAIL, DEFAULT_PASSWORD);

        user.setEmail(DEFAULT_EMAIL);
        user.setRole(DEFAULT_ROLE);
        authenticationRequestDto.setEmail(DEFAULT_EMAIL);
        authenticationRequestDto.setPassword(DEFAULT_PASSWORD);
        authenticationResponseDto.setToken(DEFAULT_TOKEN);
        authenticationResponseDto.setRole(DEFAULT_ROLE);
    }

    @Test
    void shouldAuthenticate() {
        when(userService.getUserEntityByEmail(DEFAULT_EMAIL)).thenReturn(user);
        when(jwtTokenProvider.createJwtToken(DEFAULT_EMAIL, DEFAULT_ROLE)).thenReturn(DEFAULT_TOKEN);

        AuthenticationResponseDto actual = testInstance.authenticate(authenticationRequestDto);

        verify(authenticationManager).authenticate(authentication);
        assertThat(actual)
                .isNotNull()
                .isEqualTo(authenticationResponseDto);

    }
}
