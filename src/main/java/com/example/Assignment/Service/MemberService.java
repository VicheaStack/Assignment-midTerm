package com.example.Assignment.Service;

import java.util.List;

import com.example.Assignment.DTO.LectureDTO;
import com.example.Assignment.DTO.MemberDTO;

public interface MemberService {
	MemberDTO saveMember(MemberDTO dto);

	List<MemberDTO> getAllMembers();

	MemberDTO getMemberById(Long id);

	List<LectureDTO> getEnrolledLectures(Long memberId);
}
