package com.example.Assignment.DTO;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureDTO {
	private Long id;
    private String title;
    @NotBlank(message = "Content must not be empty")
    private String content;
    private LocalDateTime createdDate;
    private List<MemberLectureDTO> registrations;
}
