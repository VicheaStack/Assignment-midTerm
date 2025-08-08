package com.example.Assignment.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Assignment.DTO.LectureDTO;
import com.example.Assignment.DTO.MemberDTO;
import com.example.Assignment.Entity.Lectures;
import com.example.Assignment.Entity.MemberLecture;
import com.example.Assignment.Mapper.LectureMapper;
import com.example.Assignment.Mapper.MemberMapper;
import com.example.Assignment.Repository.LectureRepository;
import com.example.Assignment.Repository.RegistrationLectureRepository;
import com.example.Assignment.Service.LectureService;

import jakarta.transaction.Transactional;

@Service
public class LectureServiceImpl implements LectureService {

	private final LectureRepository lectureRepository;
	private final RegistrationLectureRepository memberLectureRepository;

	public LectureServiceImpl(LectureRepository lectureRepository,
			RegistrationLectureRepository memberLectureRepository) {
		this.lectureRepository = lectureRepository;
		this.memberLectureRepository = memberLectureRepository;
	}

	@Transactional
	@Override
	public LectureDTO saveLecture(LectureDTO dto) {
		Lectures lecture = LectureMapper.toEntity(dto);
		Lectures savedLecture = lectureRepository.save(lecture);
		return LectureMapper.toDTO(savedLecture);
	}

	@Transactional
	@Override
	public List<LectureDTO> getAllLectures() {
		return lectureRepository.findAll().stream().map(LectureMapper::toDTO).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public LectureDTO getLectureById(Long id) {
		return lectureRepository.findById(id).map(LectureMapper::toDTO).orElse(null);
	}

	@Override
	public List<MemberDTO> getLectureMembers(Long lectureId) {
		// Make sure this method queries registrations by lectureId, not memberId
		List<MemberLecture> registrations = memberLectureRepository.findByLectureId(lectureId);
		return registrations.stream().map(MemberLecture::getMember).map(MemberMapper::toDTO)
				.collect(Collectors.toList());
	}

}
