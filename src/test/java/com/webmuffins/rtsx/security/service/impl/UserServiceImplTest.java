package com.webmuffins.rtsx.security.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.webmuffins.rtsx.security.dto.user.RegistrationDto;
import com.webmuffins.rtsx.security.dto.user.UserResponseDto;
import com.webmuffins.rtsx.security.entity.User;
import com.webmuffins.rtsx.security.mapper.Mapper;
import com.webmuffins.rtsx.security.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final String DEFAULT_USER_EMAIL = "email@email.em";
    private static final Long DEFAULT_ID = 1L;

    private User user;
    private RegistrationDto registrationDto;
    private UserResponseDto userResponseDto;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Mapper<User, RegistrationDto, UserResponseDto> userMapper;

    @InjectMocks
    private UserServiceImpl testInstance;

    @BeforeEach
    void setUp() {
        user = new User();
        registrationDto = new RegistrationDto();
        userResponseDto = new UserResponseDto();

        user.setEmail(DEFAULT_USER_EMAIL);
        registrationDto.setEmail(DEFAULT_USER_EMAIL);
        userResponseDto.setEmail(DEFAULT_USER_EMAIL);

        user.setId(DEFAULT_ID);
        userResponseDto.setId(DEFAULT_ID);
    }

    @Test
    void shouldGetUserEntityByEmail() {
        when(userRepository.findByEmail(DEFAULT_USER_EMAIL)).thenReturn(Optional.of(user));

        User actual = testInstance.getUserEntityByEmail(DEFAULT_USER_EMAIL);

        assertThat(actual)
                .isNotNull()
                .isEqualTo(user);
    }

    @Test
    void shouldRegisterUser() {
        when(userMapper.mapDtoToEntity(registrationDto)).thenReturn(user);

        testInstance.registerUser(registrationDto);

        verify(userRepository).save(user);
    }

    @Test
    void shouldGetUserById() {
        when(userRepository.findById(DEFAULT_ID)).thenReturn(Optional.of(user));
        when(userMapper.mapEntityToDto(user)).thenReturn(userResponseDto);

        UserResponseDto actual = testInstance.getUserById(DEFAULT_ID);

        assertThat(actual).isNotNull()
                .isEqualTo(userResponseDto);
    }

    @Test
    void shouldGetUserByEmail() {
        when(userRepository.findByEmail(DEFAULT_USER_EMAIL)).thenReturn(Optional.of(user));
        when(userMapper.mapEntityToDto(user)).thenReturn(userResponseDto);

        UserResponseDto actual = testInstance.getUserByEmail(DEFAULT_USER_EMAIL);

        assertThat(actual).isNotNull()
                .isEqualTo(userResponseDto);
    }

    @Test
    void shouldGetAllUsers() {
        List<User> allUsers = Collections.singletonList(user);
        List<UserResponseDto> allUsersResponse = Collections.singletonList(userResponseDto);

        when(userRepository.findAll()).thenReturn(allUsers);
        when(userMapper.mapEntityListToDtoList(allUsers)).thenReturn(allUsersResponse);

        List<UserResponseDto> actual = testInstance.getAllUsers();

        assertThat(actual).isNotNull()
                .isEqualTo(allUsersResponse);
    }
}
