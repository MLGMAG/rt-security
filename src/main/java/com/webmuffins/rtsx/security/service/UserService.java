package com.webmuffins.rtsx.security.service;

import java.util.List;

import com.webmuffins.rtsx.security.dto.user.RegistrationDto;
import com.webmuffins.rtsx.security.dto.user.UserResponseDto;
import com.webmuffins.rtsx.security.entity.User;

public interface UserService {

    UserResponseDto getUserById(Long id);

    User getUserEntityByEmail(String email);

    void registerUser(RegistrationDto registrationDto);

    UserResponseDto getCurrentUserInfo();

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserByEmail(String email);
}
