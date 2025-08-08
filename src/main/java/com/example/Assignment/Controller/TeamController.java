package com.example.Assignment.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Assignment.DTO.TeamDTO;
import com.example.Assignment.Service.TeamService;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/add")
    public ResponseEntity<TeamDTO> addTeam(@RequestBody TeamDTO teamDTO) {
        TeamDTO saved = teamService.saveTeam(teamDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{teamId}/edit")
    public ResponseEntity<TeamDTO> editTeam(@PathVariable Long teamId, @RequestBody TeamDTO teamDTO) {
        teamDTO.setId(teamId);
        TeamDTO updated = teamService.saveTeam(teamDTO);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/list")
	public List<TeamDTO> listTeams() {
		return teamService.getAllTeams();
    }

}
