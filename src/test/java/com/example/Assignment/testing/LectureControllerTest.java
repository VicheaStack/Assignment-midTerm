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

import com.example.Assignment.Controller.LectureController;
import com.example.Assignment.DTO.LectureDTO;
import com.example.Assignment.DTO.MemberDTO;
import com.example.Assignment.Service.LectureService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LectureController.class)
public class LectureControllerTest {

    @Autowired
    private MockMvc mockMvc;

	@MockitoBean
    private LectureService lectureService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addLecture_shouldReturnSavedLecture() throws Exception {
        LectureDTO dto = new LectureDTO();
        dto.setTitle("Physics");
        dto.setContent("Basic physics");

        LectureDTO savedDto = new LectureDTO();
        savedDto.setId(1L);
        savedDto.setTitle("Physics");

        when(lectureService.saveLecture(any(LectureDTO.class))).thenReturn(savedDto);

        mockMvc.perform(post("/api/v1/lectures/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.title").value("Physics"));
    }

    @Test
    void listLectures_shouldReturnList() throws Exception {
        LectureDTO lecture = new LectureDTO();
        lecture.setId(1L);
        lecture.setTitle("Physics");

        when(lectureService.getAllLectures()).thenReturn(List.of(lecture));

        mockMvc.perform(get("/api/v1/lectures/list"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("Physics"));
    }

    @Test
    void getLectureMembers_shouldReturnMemberList() throws Exception {
        Long lectureId = 1L;
        MemberDTO member = new MemberDTO();
        member.setId(1L);
        member.setMemberName("John");

        when(lectureService.getLectureMembers(lectureId)).thenReturn(List.of(member));

        mockMvc.perform(get("/api/v1/lectures/{lectureId}/students", lectureId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].memberName").value("John"));
    }

    // Add modify test as well (PUT)
}
