package com.example.Assignment.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Assignment.Entity.MemberLecture;

@Repository
public interface RegistrationLectureRepository extends JpaRepository<MemberLecture, Long> {

	List<MemberLecture> findByMemberId(Long memberId);

	List<MemberLecture> findByLectureId(Long lectureId);

}
