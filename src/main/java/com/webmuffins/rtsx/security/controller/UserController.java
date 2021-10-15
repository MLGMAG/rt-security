package com.webmuffins.rtsx.security.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webmuffins.rtsx.security.dto.user.AuthenticationRequestDto;
import com.webmuffins.rtsx.security.dto.user.AuthenticationResponseDto;
import com.webmuffins.rtsx.security.entity.User;
import com.webmuffins.rtsx.security.security.JwtTokenProvider;
import com.webmuffins.rtsx.security.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(AuthenticationManager authenticationManager, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/auth")
    public AuthenticationResponseDto authenticate(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDto.getEmail(), authenticationRequestDto. getPassword()));
        User user = userService.findUserByEmail(authenticationRequestDto.getEmail());
        String jwtToken = jwtTokenProvider.createJwtToken(user.getEmail(), user.getPassword());
        AuthenticationResponseDto responseDto = new AuthenticationResponseDto();
        responseDto.setToken(jwtToken);
        return responseDto;
    }

    @GetMapping
    public String test() {
        return "You are authenticated";
    }

}
