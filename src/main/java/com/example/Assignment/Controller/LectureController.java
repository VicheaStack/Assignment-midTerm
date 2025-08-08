package com.example.Assignment.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Assignment.DTO.LectureDTO;
import com.example.Assignment.DTO.MemberDTO;
import com.example.Assignment.Service.LectureService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1/lectures")
public class LectureController {

	private static final Logger logger = LoggerFactory.getLogger(LectureController.class);
    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
		super();
		this.lectureService = lectureService;
	}

	// vichea
    @PostMapping("/add")
	public ResponseEntity<LectureDTO> addLecture(@Valid @RequestBody LectureDTO lectureDTO) {
		logger.info("Add lecture called with: {}", lectureDTO);
        LectureDTO saved = lectureService.saveLecture(lectureDTO);
        return ResponseEntity.ok(saved);
    }

	// vichea
    @PutMapping("/{lectureId}/edit")
    public ResponseEntity<LectureDTO> editLecture(@PathVariable Long lectureId, @RequestBody LectureDTO lectureDTO) {
		logger.info("Edit lecture called for id: {}", lectureId);
        lectureDTO.setId(lectureId);
        LectureDTO updated = lectureService.saveLecture(lectureDTO);
        return ResponseEntity.ok(updated);
    }

	// vichea
    @GetMapping("/list")
    public ResponseEntity<List<LectureDTO>> listLectures() {
		logger.info("List lectures called");
        List<LectureDTO> lectures = lectureService.getAllLectures();
        return ResponseEntity.ok(lectures);
    }

	// vichea
    @GetMapping("/{lectureId}/students")
    public ResponseEntity<List<MemberDTO>> getLectureStudents(@PathVariable Long lectureId) {
		logger.info("Get lecture students called for id: {}", lectureId);
        List<MemberDTO> members = lectureService.getLectureMembers(lectureId);
        return ResponseEntity.ok(members);
    }
}
