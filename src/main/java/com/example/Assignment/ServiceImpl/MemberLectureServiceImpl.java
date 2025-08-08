package com.example.Assignment.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

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

	private final RegistrationLectureRepository memberLectureRepository;
	private final MemberRepository memberRepository;
    private final LectureRepository lectureRepository;

	public MemberLectureServiceImpl(RegistrationLectureRepository memberLectureRepository,
			MemberRepository memberRepository,
			LectureRepository lectureRepository) {
		this.memberLectureRepository = memberLectureRepository;
		this.memberRepository = memberRepository;
        this.lectureRepository = lectureRepository;
    }

	@Transactional
    @Override
    public MemberLectureDTO saveMemberLecture(MemberLectureDTO dto) {
		// Fetch Member entity from DB
		Member member = memberRepository.findById(dto.getMemberId())
				.orElseThrow(() -> new RuntimeException("Member not found"));

		// Fetch Lecture entity from DB
        Lectures lecture = lectureRepository.findById(dto.getLectureId())
            .orElseThrow(() -> new RuntimeException("Lecture not found"));

		// Create new registration entity
        MemberLecture registration = new MemberLecture();
		registration.setMember(member); // <-- correct entity here
        registration.setLecture(lecture);

		MemberLecture saved = memberLectureRepository.save(registration);
        return MemberLectureMapper.toDTO(saved);
    }

    @Override
    public List<MemberLectureDTO> getAllMemberLectures() {
		return memberLectureRepository.findAll().stream().map(MemberLectureMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public MemberLectureDTO getMemberLectureById(Long id) {
		return memberLectureRepository.findById(id).map(MemberLectureMapper::toDTO).orElse(null);
    }
}
