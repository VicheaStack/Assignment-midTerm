package com.example.Assignment.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.Assignment.DTO.LectureDTO;
import com.example.Assignment.DTO.MemberDTO;
import com.example.Assignment.Entity.Lectures;
import com.example.Assignment.Entity.Member;
import com.example.Assignment.Entity.MemberLecture;
import com.example.Assignment.Mapper.LectureMapper;
import com.example.Assignment.Repository.LectureRepository;
import com.example.Assignment.Repository.RegistrationLectureRepository;
import com.example.Assignment.ServiceImpl.LectureServiceImpl;

class LectureServiceImplTest {

    @Mock
    private LectureRepository lectureRepository;

    @Mock
	private RegistrationLectureRepository memberLectureRepository;

    @InjectMocks
    private LectureServiceImpl lectureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveLecture_shouldReturnSavedLectureDTO() {
        LectureDTO dto = new LectureDTO();
        dto.setTitle("Chemistry");
        dto.setContent("Basics");

        Lectures lectureEntity = LectureMapper.toEntity(dto);
        lectureEntity.setId(1L);

        when(lectureRepository.save(any(Lectures.class))).thenReturn(lectureEntity);

        LectureDTO savedDto = lectureService.saveLecture(dto);

        assertNotNull(savedDto);
        assertEquals(1L, savedDto.getId());
        assertEquals("Chemistry", savedDto.getTitle());

        verify(lectureRepository, times(1)).save(any(Lectures.class));
    }

    @Test
    void getAllLectures_shouldReturnList() {
        Lectures lecture1 = new Lectures();
        lecture1.setId(1L);
        lecture1.setTitle("Bio");

        Lectures lecture2 = new Lectures();
        lecture2.setId(2L);
        lecture2.setTitle("History");

        when(lectureRepository.findAll()).thenReturn(List.of(lecture1, lecture2));

        List<LectureDTO> lectures = lectureService.getAllLectures();

        assertEquals(2, lectures.size());
        assertEquals("Bio", lectures.get(0).getTitle());
        assertEquals("History", lectures.get(1).getTitle());

        verify(lectureRepository, times(1)).findAll();
    }

    @Test
    void getLectureMembers_shouldReturnMemberDTOList() {
        Long lectureId = 1L;

        MemberLecture reg1 = new MemberLecture();
        Member member1 = new Member();
        member1.setId(1L);
        member1.setMemberName("John");
        reg1.setMember(member1);

        MemberLecture reg2 = new MemberLecture();
        Member member2 = new Member();
        member2.setId(2L);
        member2.setMemberName("Jane");
        reg2.setMember(member2);

		when(memberLectureRepository.findByLectureId(lectureId)).thenReturn(List.of(reg1, reg2));

        List<MemberDTO> members = lectureService.getLectureMembers(lectureId);

        assertEquals(2, members.size());
        assertEquals("John", members.get(0).getMemberName());
        assertEquals("Jane", members.get(1).getMemberName());

		verify(memberLectureRepository, times(1)).findByLectureId(lectureId);
    }
}
