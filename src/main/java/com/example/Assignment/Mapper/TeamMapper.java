package com.example.Assignment.Mapper;

import com.example.Assignment.DTO.TeamDTO;
import com.example.Assignment.Entity.Team;

public class TeamMapper {

    public static TeamDTO toDTO(Team entity) {
        if (entity == null) return null;

        TeamDTO dto = new TeamDTO();
        dto.setId(entity.getId());
		dto.setTeamName(entity.getName()); // maps to DTO creationDate
        return dto;
    }

    public static Team toEntity(TeamDTO dto) {
        if (dto == null) return null;

        Team entity = new Team();
        entity.setId(dto.getId());
		entity.setName(dto.getTeamName());
        return entity;
    }
}
