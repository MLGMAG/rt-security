package com.webmuffins.rtsx.security.filter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.webmuffins.rtsx.security.exception.InvalidTokenException;
import com.webmuffins.rtsx.security.security.JwtTokenProvider;

@ExtendWith({MockitoExtension.class})
class JwtTokenFilterTest {

    private static final String DEFAULT_TOKEN = "token";

    @Mock
    private UsernamePasswordAuthenticationToken authentication;

    @Mock
    private HttpServletRequest servletRequest;

    @Mock
    private HttpServletResponse servletResponse;

    @Mock
    private FilterChain filterChain;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private JwtTokenFilter testInstance;


    @Test
    void shouldPassRequest() throws ServletException, IOException {
        when(jwtTokenProvider.resolveToken(servletRequest)).thenReturn(DEFAULT_TOKEN);
        when(jwtTokenProvider.getAuthentication(DEFAULT_TOKEN)).thenReturn(authentication);

        testInstance.doFilter(servletRequest, servletResponse, filterChain);

        verify(jwtTokenProvider).validateToken(DEFAULT_TOKEN);
        verify(servletResponse, never()).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Test
    void shouldNotPassRequest() throws ServletException, IOException {
        when(jwtTokenProvider.resolveToken(servletRequest)).thenReturn(DEFAULT_TOKEN);
        doThrow(InvalidTokenException.class).when(jwtTokenProvider).validateToken(DEFAULT_TOKEN);

        testInstance.doFilter(servletRequest, servletResponse, filterChain);

        verify(servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, null);
    }
}
