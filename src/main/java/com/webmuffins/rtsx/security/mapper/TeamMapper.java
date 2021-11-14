package com.webmuffins.rtsx.security.mapper;

import com.webmuffins.rtsx.security.dto.team.TeamRequestDto;
import com.webmuffins.rtsx.security.dto.team.TeamResponseDto;
import com.webmuffins.rtsx.security.entity.Team;

public class TeamMapper implements Mapper<Team, TeamRequestDto, TeamResponseDto> {

    @Override
    public TeamResponseDto mapEntityToDto(Team team) {
        return null;
    }

    @Override
    public Team mapDtoToEntity(TeamRequestDto teamRequestDto) {
        return null;
    }
}
