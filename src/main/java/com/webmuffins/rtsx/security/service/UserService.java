package com.webmuffins.rtsx.security.service;

import com.webmuffins.rtsx.security.entity.User;

public interface UserService {

    User findUserByEmail(String email);

}
