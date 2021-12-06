package com.webmuffins.rtsx.security.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.webmuffins.rtsx.security.dto.user.RegistrationDto;
import com.webmuffins.rtsx.security.dto.user.UserData;
import com.webmuffins.rtsx.security.dto.user.UserResponseDto;
import com.webmuffins.rtsx.security.entity.User;
import com.webmuffins.rtsx.security.exception.NotFoundException;
import com.webmuffins.rtsx.security.mapper.Mapper;
import com.webmuffins.rtsx.security.repository.UserRepository;
import com.webmuffins.rtsx.security.service.UserService;

@Service
@ComponentScan("com.com.webmuffins.rtsx.security.repository")
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final Mapper<User, RegistrationDto, UserResponseDto> userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(Mapper<User, RegistrationDto, UserResponseDto> userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can not find user with such id"));
        return userMapper.mapEntityToDto(user);
    }



    @Override
    public User getUserEntityByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Can not find user with such email"));
    }

    @Override
    public void registerUser(RegistrationDto registrationDto) {
        User user = userMapper.mapDtoToEntity(registrationDto);
        userRepository.save(user);
        LOG.info("Created user : {}", user);
    }

    @Override
    public UserResponseDto getCurrentUserInfo() {
        UserData currentUserData = (UserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = getUserEntityByEmail(currentUserData.getUsername());
        return userMapper.mapEntityToDto(currentUser);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.mapEntityListToDtoList(users);
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        User user = getUserEntityByEmail(email);
        return userMapper.mapEntityToDto(user);
    }

}
