package com.webmuffins.rtsx.security.service.impl;

import org.springframework.stereotype.Service;

import com.webmuffins.rtsx.security.entity.User;
import com.webmuffins.rtsx.security.exception.NotFoundException;
import com.webmuffins.rtsx.security.repository.UserJPARepository;
import com.webmuffins.rtsx.security.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserJPARepository userJPARepository;

    public UserServiceImpl(UserJPARepository userJPARepository) {
        this.userJPARepository = userJPARepository;
    }

    @Override
    public User findUserByEmail(String email) {
        return userJPARepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Can not find user with such email"));
    }
}
