package com.example.Assignment.Service;

import java.util.List;

import com.example.Assignment.DTO.TeamDTO;

public interface TeamService {
	TeamDTO saveTeam(TeamDTO dto);

	List<TeamDTO> getAllTeams();

	TeamDTO getTeamById(Long id);
}
