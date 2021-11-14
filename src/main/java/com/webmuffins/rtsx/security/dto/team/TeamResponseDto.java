package com.webmuffins.rtsx.security.dto.team;

import java.util.List;
import java.util.UUID;

import com.webmuffins.rtsx.security.dto.user.UserResponseDto;

public class TeamResponseDto {

    private UUID id;
    private String name;
    private UUID boardId;
    private List<UserResponseDto> users;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getBoardId() {
        return boardId;
    }

    public void setBoardId(UUID boardId) {
        this.boardId = boardId;
    }

    public List<UserResponseDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserResponseDto> users) {
        this.users = users;
    }
}
