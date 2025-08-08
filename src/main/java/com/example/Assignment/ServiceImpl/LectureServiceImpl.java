package com.example.Assignment.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static final Logger logger = LogManager.getLogger(LectureServiceImpl.class);

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
		logger.info("Saving lecture: {}", dto);
		Lectures lecture = LectureMapper.toEntity(dto);
		Lectures savedLecture = lectureRepository.save(lecture);
		logger.info("Lecture saved with ID: {}", savedLecture.getId());
		return LectureMapper.toDTO(savedLecture);
	}

	@Transactional
	@Override
	public List<LectureDTO> getAllLectures() {
		logger.info("Fetching all lectures");
		List<LectureDTO> lectures = lectureRepository.findAll().stream().map(LectureMapper::toDTO)
				.collect(Collectors.toList());
		logger.info("Number of lectures fetched: {}", lectures.size());
		return lectures;
	}

	@Transactional
	@Override
	public LectureDTO getLectureById(Long id) {
		logger.info("Fetching lecture by ID: {}", id);
		LectureDTO dto = lectureRepository.findById(id).map(LectureMapper::toDTO).orElse(null);
		if (dto == null) {
			logger.warn("Lecture not found with ID: {}", id);
		} else {
			logger.info("Lecture found: {}", dto);
		}
		return dto;
	}

	@Override
	public List<MemberDTO> getLectureMembers(Long lectureId) {
		logger.info("Fetching members enrolled in lecture ID: {}", lectureId);
		List<MemberLecture> registrations = memberLectureRepository.findByLectureId(lectureId);
		List<MemberDTO> members = registrations.stream().map(MemberLecture::getMember).map(MemberMapper::toDTO)
				.collect(Collectors.toList());
		logger.info("Number of members found for lecture ID {}: {}", lectureId, members.size());
		return members;
	}
}
