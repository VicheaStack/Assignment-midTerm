package com.example.Assignment.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

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
		Member member;

		if (dto.getId() != null) {
			// Update existing member
			member = memberRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Member not found"));

			// Update fields except creationDate
			member.setMemberName(dto.getMemberName());
			member.setAge(dto.getAge());
			member.setAddress(dto.getAddress());
		} else {
			// Create new member from DTO
			member = MemberMapper.toEntity(dto);
		}

		// Set the team, required in both create and update
		if (dto.getTeamId() != null) {
			Team team = teamRepository.findById(dto.getTeamId())
					.orElseThrow(() -> new RuntimeException("Team not found"));
			member.setTeam(team);
		} else {
			throw new RuntimeException("Team is required");
		}

		// Save and return DTO
		Member savedMember = memberRepository.save(member);
		return MemberMapper.toDTO(savedMember);
	}

	@Transactional
	@Override
	public List<MemberDTO> getAllMembers() {
		return memberRepository.findAll().stream().map(MemberMapper::toDTO).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public MemberDTO getMemberById(Long id) {
		return memberRepository.findById(id).map(MemberMapper::toDTO).orElse(null);
	}

	@Transactional
	@Override
	public List<LectureDTO> getEnrolledLectures(Long memberId) {
		List<MemberLecture> registrations = memberLectureRepository.findByMemberId(memberId);
		return registrations.stream().map(MemberLecture::getLecture).map(LectureMapper::toDTO)
				.collect(Collectors.toList());
	}
}
