package com.example.Assignment.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.Assignment.DTO.LectureDTO;
import com.example.Assignment.DTO.MemberLectureDTO;
import com.example.Assignment.Entity.Lectures;

public class LectureMapper {

    public static LectureDTO toDTO(Lectures lecture) {
        if (lecture == null) return null;

		LectureDTO dto = new LectureDTO();
		dto.setId(lecture.getId());
		dto.setTitle(lecture.getTitle());
		dto.setContent(lecture.getContent());
		dto.setCreatedDate(lecture.getCreatedDate());

		List<MemberLectureDTO> regDtos = lecture.getRegistration() == null ? new ArrayList<>()
				: lecture.getRegistration().stream().map(MemberLectureMapper::toDTO).collect(Collectors.toList());

		dto.setRegistrations(regDtos);

		return dto;
    }

    public static Lectures toEntity(LectureDTO dto) {
        if (dto == null) return null;

        Lectures lecture = new Lectures();
        lecture.setId(dto.getId());
        lecture.setTitle(dto.getTitle());
        lecture.setContent(dto.getContent());
		// Optionally set createdDate if needed
		// lecture.setCreatedDate(dto.getCreatedDate());

		// Do NOT set registrations here; this is usually handled separately in service
		// layer.

        return lecture;
    }
}
