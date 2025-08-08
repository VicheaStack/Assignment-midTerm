package com.example.Assignment.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.Assignment.DTO.TeamDTO;
import com.example.Assignment.Entity.Team;
import com.example.Assignment.Mapper.TeamMapper;
import com.example.Assignment.Repository.TeamRepository;
import com.example.Assignment.Service.TeamService;

import jakarta.transaction.Transactional;

@Service
public class TeamServiceImpl implements TeamService {

	private static final Logger logger = LogManager.getLogger(TeamServiceImpl.class);

	private final TeamRepository teamRepository;

	public TeamServiceImpl(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	@Transactional
	@Override
	public TeamDTO saveTeam(TeamDTO dto) {
		logger.info("Saving team: {}", dto);
		Team team = TeamMapper.toEntity(dto);
		Team savedTeam = teamRepository.save(team);
		logger.info("Team saved with ID: {}", savedTeam.getId());
		return TeamMapper.toDTO(savedTeam);
	}

	@Transactional
	@Override
	public List<TeamDTO> getAllTeams() {
		logger.info("Fetching all teams");
		List<TeamDTO> teams = teamRepository.findAll().stream().map(TeamMapper::toDTO).collect(Collectors.toList());
		logger.info("Number of teams fetched: {}", teams.size());
		return teams;
	}

	@Transactional
	@Override
	public TeamDTO getTeamById(Long id) {
		logger.info("Fetching team by ID: {}", id);
		TeamDTO dto = teamRepository.findById(id).map(TeamMapper::toDTO).orElse(null);
		if (dto == null) {
			logger.warn("Team not found with ID: {}", id);
		} else {
			logger.info("Team found: {}", dto);
		}
		return dto;
	}
}
