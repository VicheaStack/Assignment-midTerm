package com.example.Assignment.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Assignment.DTO.MemberLectureDTO;
import com.example.Assignment.Entity.Lectures;
import com.example.Assignment.Entity.Member;
import com.example.Assignment.Entity.MemberLecture;
import com.example.Assignment.Mapper.MemberLectureMapper;
import com.example.Assignment.Repository.LectureRepository;
import com.example.Assignment.Repository.MemberRepository;
import com.example.Assignment.Repository.RegistrationLectureRepository;
import com.example.Assignment.Service.MemberLectureService;

@Service
public class MemberLectureServiceImpl implements MemberLectureService {

	private static final Logger logger = LogManager.getLogger(MemberLectureServiceImpl.class);

	private final RegistrationLectureRepository memberLectureRepository;
	private final MemberRepository memberRepository;
    private final LectureRepository lectureRepository;

	public MemberLectureServiceImpl(RegistrationLectureRepository memberLectureRepository,
			MemberRepository memberRepository, LectureRepository lectureRepository) {
		this.memberLectureRepository = memberLectureRepository;
		this.memberRepository = memberRepository;
        this.lectureRepository = lectureRepository;
    }

	@Transactional
    @Override
    public MemberLectureDTO saveMemberLecture(MemberLectureDTO dto) {
		logger.info("Saving MemberLecture registration for Member ID: {} and Lecture ID: {}", dto.getMemberId(),
				dto.getLectureId());

		Member member = memberRepository.findById(dto.getMemberId()).orElseThrow(() -> {
			logger.error("Member not found with ID: {}", dto.getMemberId());
			return new RuntimeException("Member not found");
		});

        Lectures lecture = lectureRepository.findById(dto.getLectureId())
				.orElseThrow(() -> {
					logger.error("Lecture not found with ID: {}", dto.getLectureId());
					return new RuntimeException("Lecture not found");
				});

        MemberLecture registration = new MemberLecture();
		registration.setMember(member);
        registration.setLecture(lecture);

		MemberLecture saved = memberLectureRepository.save(registration);
		logger.info("MemberLecture registration saved with ID: {}", saved.getId());

        return MemberLectureMapper.toDTO(saved);
    }

    @Override
    public List<MemberLectureDTO> getAllMemberLectures() {
		logger.info("Fetching all MemberLecture registrations");
		List<MemberLectureDTO> dtos = memberLectureRepository.findAll().stream().map(MemberLectureMapper::toDTO)
				.collect(Collectors.toList());
		logger.info("Number of MemberLecture registrations found: {}", dtos.size());
		return dtos;
    }

    @Override
    public MemberLectureDTO getMemberLectureById(Long id) {
		logger.info("Fetching MemberLecture registration by ID: {}", id);
		MemberLectureDTO dto = memberLectureRepository.findById(id).map(MemberLectureMapper::toDTO).orElse(null);
		if (dto == null) {
			logger.warn("MemberLecture registration not found with ID: {}", id);
		} else {
			logger.info("MemberLecture registration found: {}", dto);
		}
		return dto;
    }
}
