package com.example.Assignment.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.Assignment.DTO.TeamDTO;
import com.example.Assignment.Entity.Team;
import com.example.Assignment.Mapper.TeamMapper;
import com.example.Assignment.Repository.TeamRepository;
import com.example.Assignment.ServiceImpl.TeamServiceImpl;

class TeamServiceImplTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamServiceImpl teamService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveTeam_shouldReturnSavedTeamDTO() {
        TeamDTO dto = new TeamDTO();
        dto.setTeamName("Team A");
		dto.setCreationDate(LocalDateTime.now());

        Team teamEntity = TeamMapper.toEntity(dto);
        teamEntity.setId(1L);

        when(teamRepository.save(any(Team.class))).thenReturn(teamEntity);

        TeamDTO savedDto = teamService.saveTeam(dto);

        assertNotNull(savedDto);
        assertEquals(1L, savedDto.getId());
        assertEquals("Team A", savedDto.getTeamName());

        verify(teamRepository, times(1)).save(any(Team.class));
    }

    @Test
    void getAllTeams_shouldReturnListOfTeamDTOs() {
        Team team1 = new Team();
        team1.setId(1L);
        team1.setName("Team 1");
        team1.setCreatedDate(LocalDateTime.now());

        Team team2 = new Team();
        team2.setId(2L);
        team2.setName("Team 2");
        team2.setCreatedDate(LocalDateTime.now());

        when(teamRepository.findAll()).thenReturn(Arrays.asList(team1, team2));

        var list = teamService.getAllTeams();

        assertEquals(2, list.size());
        assertEquals("Team 1", list.get(0).getTeamName());
        assertEquals("Team 2", list.get(1).getTeamName());

        verify(teamRepository, times(1)).findAll();
    }

    @Test
    void getTeamById_shouldReturnTeamDTO() {
        Team team = new Team();
        team.setId(1L);
        team.setName("Team X");
        team.setCreatedDate(LocalDateTime.now());

        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));

        TeamDTO dto = teamService.getTeamById(1L);

        assertNotNull(dto);
        assertEquals("Team X", dto.getTeamName());
        verify(teamRepository, times(1)).findById(1L);
    }
}
