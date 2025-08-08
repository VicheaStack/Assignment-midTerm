package com.example.Assignment.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Assignment.DTO.MemberDTO;
import com.example.Assignment.Entity.Member;
import com.example.Assignment.Entity.Team;
import com.example.Assignment.Mapper.MemberMapper;
import com.example.Assignment.Repository.MemberRepository;
import com.example.Assignment.Repository.TeamRepository;
import com.example.Assignment.ServiceImpl.MemberServiceImpl;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

	@Mock
	private TeamRepository teamRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Test
    void saveMember_shouldReturnSavedMemberDTO() {
        MemberDTO dto = new MemberDTO();
        dto.setMemberName("John");
        dto.setAge(25);
        dto.setAddress("123 Street");
		dto.setTeamId(1L); // important!

		Team team = new Team();
		team.setId(1L);
		team.setName("Test Team");

		when(teamRepository.findById(1L)).thenReturn(Optional.of(team));

        Member memberEntity = MemberMapper.toEntity(dto);
        memberEntity.setId(1L);
		memberEntity.setTeam(team); // make sure team is set

        when(memberRepository.save(any(Member.class))).thenReturn(memberEntity);

        MemberDTO savedDto = memberService.saveMember(dto);

        assertNotNull(savedDto);
        assertEquals(1L, savedDto.getId());
        assertEquals("John", savedDto.getMemberName());

        verify(memberRepository, times(1)).save(any(Member.class));
    }

}
