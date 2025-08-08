package com.example.Assignment.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Assignment.Controller.TeamController;
import com.example.Assignment.DTO.TeamDTO;
import com.example.Assignment.Service.TeamService;

@ExtendWith(MockitoExtension.class)
class TeamControllerTest {

    @Mock
    private TeamService teamService; // mock the service

    @InjectMocks
    private TeamController teamController; // inject the mock into controller

    @BeforeEach
    void setUp() {
        // Setup before each test if needed
    }

    @Test
    void listTeams_shouldReturnList() {
        // Arrange
        TeamDTO team = new TeamDTO(1L, "TeamA", LocalDateTime.now());
        when(teamService.getAllTeams()).thenReturn(List.of(team));

        // Act
		List<TeamDTO> result = teamController.listTeams();

        // Assert
        assertEquals(1, result.size());
        assertEquals("TeamA", result.get(0).getTeamName());
    }
}
