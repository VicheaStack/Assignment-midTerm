package com.example.Assignment.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MemberLectureDTO {
    private Long id;
    private Long memberId;
    private Long lectureId;
    private LocalDateTime registrationDate;
}
