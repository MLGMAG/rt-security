package com.webmuffins.rtsx.security.dto.team;

import java.util.UUID;

import javax.validation.constraints.NotNull;

public class TeamRequestDto {

    @NotNull
    private String name;
    private UUID boardId;

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
}
