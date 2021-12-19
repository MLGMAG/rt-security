package com.webmuffins.rtsx.security.dto.user;

import com.webmuffins.rtsx.security.constant.Role;
import com.webmuffins.rtsx.security.entity.Team;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Objects;
import java.util.StringJoiner;

public class AuthenticationResponseDto {

    private String token;

    private String firstName;

    private String lastName;

    private Team team;

    private Role role;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationResponseDto that = (AuthenticationResponseDto) o;
        return Objects.equals(token, that.token) && Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) && Objects.equals(team, that.team) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, firstName, lastName, team, role);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AuthenticationResponseDto.class.getSimpleName() + "[", "]")
                .add("token='" + token + "'")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("team=" + team)
                .add("role=" + role)
                .toString();
    }
}
