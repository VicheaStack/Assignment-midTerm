package com.example.Assignment.testing;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Assignment.Controller.MemberController;
import com.example.Assignment.DTO.LectureDTO;
import com.example.Assignment.DTO.MemberDTO;
import com.example.Assignment.Service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

	@MockitoBean
    private MemberService memberService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addMember_shouldReturnSavedMember() throws Exception {
        MemberDTO dto = new MemberDTO();
        dto.setMemberName("John");
        dto.setAge(25);
        dto.setAddress("123 Street");
        dto.setTeamId(1L);

        MemberDTO savedDto = new MemberDTO();
        savedDto.setId(1L);
        savedDto.setMemberName("John");

        when(memberService.saveMember(any(MemberDTO.class))).thenReturn(savedDto);

        mockMvc.perform(post("/api/v1/members/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.memberName").value("John"));
    }

    @Test
    void listMembers_shouldReturnList() throws Exception {
        MemberDTO member = new MemberDTO();
        member.setId(1L);
        member.setMemberName("John");

        when(memberService.getAllMembers()).thenReturn(List.of(member));

        mockMvc.perform(get("/api/v1/members/list"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].memberName").value("John"));
    }

    @Test
    void getEnrolledLectures_shouldReturnLectureList() throws Exception {
        Long memberId = 1L;
        LectureDTO lecture = new LectureDTO();
        lecture.setId(1L);
        lecture.setTitle("Math");

        when(memberService.getEnrolledLectures(memberId)).thenReturn(List.of(lecture));

        mockMvc.perform(get("/api/v1/members/{memberId}/lectures", memberId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("Math"));
    }

    // Add modify test as well (PUT)
}
