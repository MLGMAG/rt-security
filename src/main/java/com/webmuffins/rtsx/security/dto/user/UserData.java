package com.webmuffins.rtsx.security.dto.user;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.webmuffins.rtsx.security.entity.User;

public class UserData implements UserDetails {

    private String username;
    private String password;
    private Set<SimpleGrantedAuthority> authorities;
    private static boolean isActive = true;

    public UserData() {
    }

    public UserData(String username, String password, Set<SimpleGrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails mapUserToUserDetails(User user) {
        return new UserData(user.getEmail(), user.getPassword(), user.getRole().getAuthorities());
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthorities(Set<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserData)) {
            return false;
        }
        UserData userData = (UserData) o;
        return Objects.equals(getUsername(), userData.getUsername()) && Objects.equals(getPassword(), userData.getPassword()) && Objects
                .equals(getAuthorities(), userData.getAuthorities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getAuthorities());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserData.class.getSimpleName() + "[", "]").add("username='" + username + "'").add("password='" + password + "'")
                .add("authorities=" + authorities).toString();
    }
}
