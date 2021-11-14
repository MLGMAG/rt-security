package com.webmuffins.rtsx.security.service;

import com.webmuffins.rtsx.security.dto.user.RegistrationDto;
import com.webmuffins.rtsx.security.entity.User;

public interface UserService {

    User findUserByEmail(String email);

    void registerUser(RegistrationDto registrationDto);

}
