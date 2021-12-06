package com.webmuffins.rtsx.security.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.webmuffins.rtsx.security.constant.Role;
import com.webmuffins.rtsx.security.dto.user.RegistrationDto;
import com.webmuffins.rtsx.security.dto.user.UserResponseDto;
import com.webmuffins.rtsx.security.entity.User;

@Component
public class UserMapper implements Mapper<User, RegistrationDto, UserResponseDto>{

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto mapEntityToDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setRole(user.getRole());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setId(user.getId());
        return dto;
    }

    @Override
    public User mapDtoToEntity(RegistrationDto dto) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setRole(Role.USER);
        return user;
    }

}
