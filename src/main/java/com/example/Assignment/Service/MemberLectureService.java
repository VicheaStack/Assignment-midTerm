package com.example.Assignment.Service;

import java.util.List;

import com.example.Assignment.DTO.MemberLectureDTO;

public interface MemberLectureService {
    MemberLectureDTO saveMemberLecture(MemberLectureDTO dto);
    List<MemberLectureDTO> getAllMemberLectures();
    MemberLectureDTO getMemberLectureById(Long id);
}
