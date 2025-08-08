package com.example.Assignment.Mapper;

import com.example.Assignment.DTO.MemberDTO;
import com.example.Assignment.Entity.Member;

public class MemberMapper {

    public static MemberDTO toDTO(Member entity) {
        if (entity == null) return null;

        MemberDTO dto = new MemberDTO();
        dto.setId(entity.getId());
        dto.setMemberName(entity.getMemberName());
        dto.setAge(entity.getAge());
        dto.setAddress(entity.getAddress());
		dto.setCreationDate(entity.getCreationDate()); // read-only, set by JPA
        if (entity.getTeam() != null) {
            dto.setTeamId(entity.getTeam().getId());
        }
        return dto;
    }

    public static Member toEntity(MemberDTO dto) {
        if (dto == null) return null;

        Member entity = new Member();
        entity.setId(dto.getId());
        entity.setMemberName(dto.getMemberName());
        entity.setAge(dto.getAge());
        entity.setAddress(dto.getAddress());
		// Do NOT set creationDate here. Let JPA auditing handle it.
		// entity.setCreationDate(dto.getCreationDate());
		// Set Team entity separately in service
        return entity;
    }
}
