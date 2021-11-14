package com.webmuffins.rtsx.security.security;

import static com.webmuffins.rtsx.security.constant.HttpConstant.AUTHORIZATION_HEADER;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.webmuffins.rtsx.security.constant.Role;
import com.webmuffins.rtsx.security.exception.InvalidTokenException;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.validity-time}")
    private long validityTime;

    private final UserDetailsService userDetailsService;

    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String createJwtToken(String email, Role role) {
        Claims claims = Jwts.claims()
                .setSubject(email);
        claims.put("role", role.getName());
        Date now = new Date();
        Date expiration = new Date(now.getTime() + validityTime * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public void validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            validateTokenExpiration(claimsJws);
        } catch (Exception e) {
            throw new InvalidTokenException(e.getMessage());
        }
    }

    private void validateTokenExpiration(Jws<Claims> claimsJws) {
        boolean before = claimsJws.getBody().getExpiration().before(new Date());
        if(before) {
            throw new InvalidTokenException("Token is expired");
        }
    }

    public Authentication getAuthentication(String token) {
        String email = getEmailFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION_HEADER);
    }

}
