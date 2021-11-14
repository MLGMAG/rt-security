package com.webmuffins.rtsx.security.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.webmuffins.rtsx.security.dto.user.RegistrationDto;
import com.webmuffins.rtsx.security.entity.User;
import com.webmuffins.rtsx.security.exception.NotFoundException;
import com.webmuffins.rtsx.security.mapper.UserMapper;
import com.webmuffins.rtsx.security.repository.UserRepository;
import com.webmuffins.rtsx.security.service.UserService;

@Service
@ComponentScan("com.com.webmuffins.rtsx.security.repository")
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Can not find user with such email"));
    }

    @Override
    public void registerUser(RegistrationDto registrationDto) {
        User user = userMapper.mapDtoToEntity(registrationDto);
        userRepository.save(user);
        LOG.info("Created user : {}", user);
    }

}
