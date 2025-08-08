package com.example.Assignment.Service;

import java.util.List;

import com.example.Assignment.DTO.LectureDTO;
import com.example.Assignment.DTO.MemberDTO;

public interface LectureService {
	LectureDTO saveLecture(LectureDTO dto);

	List<LectureDTO> getAllLectures();

	LectureDTO getLectureById(Long id);

	List<MemberDTO> getLectureMembers(Long lectureId);
}
