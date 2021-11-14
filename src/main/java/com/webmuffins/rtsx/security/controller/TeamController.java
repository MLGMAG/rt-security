package com.webmuffins.rtsx.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.webmuffins.rtsx.security.dto.team.TeamRequestDto;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTeam(@RequestBody TeamRequestDto dto) {

    }

}
