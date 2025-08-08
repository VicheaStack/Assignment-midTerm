package com.example.Assignment.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Assignment.DTO.LectureDTO;
import com.example.Assignment.DTO.MemberDTO;
import com.example.Assignment.Entity.Member;
import com.example.Assignment.Entity.MemberLecture;
import com.example.Assignment.Entity.Team;
import com.example.Assignment.Mapper.LectureMapper;
import com.example.Assignment.Mapper.MemberMapper;
import com.example.Assignment.Repository.MemberRepository;
import com.example.Assignment.Repository.RegistrationLectureRepository;
import com.example.Assignment.Repository.TeamRepository;
import com.example.Assignment.Service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	private static final Logger logger = LogManager.getLogger(MemberServiceImpl.class);

	private final MemberRepository memberRepository;
	private final TeamRepository teamRepository;
	private final RegistrationLectureRepository memberLectureRepository;

	public MemberServiceImpl(MemberRepository memberRepository, TeamRepository teamRepository,
			RegistrationLectureRepository memberLectureRepository) {
		this.memberRepository = memberRepository;
		this.teamRepository = teamRepository;
		this.memberLectureRepository = memberLectureRepository;
	}

	@Transactional
	@Override
	public MemberDTO saveMember(MemberDTO dto) {
		logger.info("Saving member: {}", dto);
		Member member;

		if (dto.getId() != null) {
			logger.debug("Updating existing member with ID: {}", dto.getId());
			member = memberRepository.findById(dto.getId()).orElseThrow(() -> {
				logger.error("Member not found with ID: {}", dto.getId());
				return new RuntimeException("Member not found");
			});

			member.setMemberName(dto.getMemberName());
			member.setAge(dto.getAge());
			member.setAddress(dto.getAddress());
		} else {
			logger.debug("Creating new member");
			member = MemberMapper.toEntity(dto);
		}

		if (dto.getTeamId() != null) {
			Team team = teamRepository.findById(dto.getTeamId()).orElseThrow(() -> {
				logger.error("Team not found with ID: {}", dto.getTeamId());
				return new RuntimeException("Team not found");
			});
			member.setTeam(team);
		} else {
			logger.error("Team is required but not provided");
			throw new RuntimeException("Team is required");
		}

		Member savedMember = memberRepository.save(member);
		logger.info("Member saved with ID: {}", savedMember.getId());
		return MemberMapper.toDTO(savedMember);
	}

	@Transactional
	@Override
	public List<MemberDTO> getAllMembers() {
		logger.info("Fetching all members");
		List<MemberDTO> members = memberRepository.findAll().stream().map(MemberMapper::toDTO)
				.collect(Collectors.toList());
		logger.info("Number of members fetched: {}", members.size());
		return members;
	}

	@Transactional
	@Override
	public MemberDTO getMemberById(Long id) {
		logger.info("Fetching member by ID: {}", id);
		MemberDTO dto = memberRepository.findById(id).map(MemberMapper::toDTO).orElse(null);
		if (dto == null) {
			logger.warn("Member not found with ID: {}", id);
		} else {
			logger.info("Member found: {}", dto);
		}
		return dto;
	}

	@Transactional
	@Override
	public List<LectureDTO> getEnrolledLectures(Long memberId) {
		logger.info("Fetching enrolled lectures for member ID: {}", memberId);
		List<MemberLecture> registrations = memberLectureRepository.findByMemberId(memberId);
		List<LectureDTO> lectures = registrations.stream().map(MemberLecture::getLecture).map(LectureMapper::toDTO)
				.collect(Collectors.toList());
		logger.info("Number of enrolled lectures found: {}", lectures.size());
		return lectures;
	}
}
