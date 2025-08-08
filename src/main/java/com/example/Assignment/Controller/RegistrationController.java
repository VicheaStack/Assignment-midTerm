package com.example.Assignment.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Assignment.DTO.MemberLectureDTO;
import com.example.Assignment.Service.MemberLectureService;

@RestController
@RequestMapping("/api/v1/registrations")
public class RegistrationController {

    private final MemberLectureService memberLectureService;

    public RegistrationController(MemberLectureService memberLectureService) {
        this.memberLectureService = memberLectureService;
    }

    @PostMapping("/add")
    public ResponseEntity<MemberLectureDTO> addRegistration(@RequestBody MemberLectureDTO dto) {
        MemberLectureDTO saved = memberLectureService.saveMemberLecture(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/list")
    public ResponseEntity<List<MemberLectureDTO>> listRegistrations() {
        List<MemberLectureDTO> registrations = memberLectureService.getAllMemberLectures();
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberLectureDTO> getRegistration(@PathVariable Long id) {
        MemberLectureDTO dto = memberLectureService.getMemberLectureById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }
}
