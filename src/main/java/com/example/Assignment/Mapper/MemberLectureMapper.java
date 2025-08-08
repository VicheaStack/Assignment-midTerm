package com.example.Assignment.Mapper;

import com.example.Assignment.DTO.MemberLectureDTO;
import com.example.Assignment.Entity.MemberLecture;

public class MemberLectureMapper {

    public static MemberLectureDTO toDTO(MemberLecture entity) {
        if (entity == null) return null;

		MemberLectureDTO dto = new MemberLectureDTO();
		dto.setId(entity.getId());

		if (entity.getMember() != null) {
			dto.setMemberId(entity.getMember().getId());
		}

		if (entity.getLecture() != null) {
			dto.setLectureId(entity.getLecture().getId());
		}

		dto.setRegistrationDate(entity.getRegistrationDate());

		return dto;
    }

    public static MemberLecture toEntity(MemberLectureDTO dto) {
        if (dto == null) return null;

        MemberLecture entity = new MemberLecture();
		entity.setId(dto.getId());
		// member and lectures should be set in the service layer separately
        entity.setRegistrationDate(dto.getRegistrationDate());
        return entity;
    }
}
