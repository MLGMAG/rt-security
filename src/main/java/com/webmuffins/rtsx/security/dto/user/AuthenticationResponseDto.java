package com.webmuffins.rtsx.security.dto.user;

import java.util.Objects;
import java.util.StringJoiner;

public class AuthenticationResponseDto {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuthenticationResponseDto)) {
            return false;
        }
        AuthenticationResponseDto that = (AuthenticationResponseDto) o;
        return Objects.equals(getToken(), that.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AuthenticationResponseDto.class.getSimpleName() + "[", "]").add("token='" + token + "'").toString();
    }
}
