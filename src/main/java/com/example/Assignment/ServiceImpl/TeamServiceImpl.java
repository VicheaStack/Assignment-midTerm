package com.example.Assignment.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Assignment.DTO.TeamDTO;
import com.example.Assignment.Entity.Team;
import com.example.Assignment.Mapper.TeamMapper;
import com.example.Assignment.Repository.TeamRepository;
import com.example.Assignment.Service.TeamService;

import jakarta.transaction.Transactional;

@Service
public class TeamServiceImpl implements TeamService {

	private final TeamRepository teamRepository;

	public TeamServiceImpl(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	@Transactional
	@Override
	public TeamDTO saveTeam(TeamDTO dto) {
		Team team = TeamMapper.toEntity(dto);
		Team savedTeam = teamRepository.save(team);
		return TeamMapper.toDTO(savedTeam);
	}

	@Transactional
	@Override
	public List<TeamDTO> getAllTeams() {
		return teamRepository.findAll().stream().map(TeamMapper::toDTO).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public TeamDTO getTeamById(Long id) {
		return teamRepository.findById(id).map(TeamMapper::toDTO).orElse(null);
	}
}
