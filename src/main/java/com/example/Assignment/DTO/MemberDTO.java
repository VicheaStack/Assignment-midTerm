package com.example.Assignment.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MemberDTO {
    private Long id;
    private String memberName;
    private int age;
    private String address;
	private LocalDateTime creationDate; // changed from LocalDate to LocalDateTime
	private Long teamId;
}
