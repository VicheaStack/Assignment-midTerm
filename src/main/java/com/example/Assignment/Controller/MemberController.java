package com.example.Assignment.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Assignment.DTO.LectureDTO;
import com.example.Assignment.DTO.MemberDTO;
import com.example.Assignment.Service.MemberService;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/add")
    public ResponseEntity<MemberDTO> addMember(@RequestBody MemberDTO memberDTO) {
        MemberDTO saved = memberService.saveMember(memberDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{memberId}/edit")
    public ResponseEntity<MemberDTO> editMember(@PathVariable Long memberId, @RequestBody MemberDTO memberDTO) {
		memberDTO.setId(memberId);
        MemberDTO updated = memberService.saveMember(memberDTO);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/list")
    public ResponseEntity<List<MemberDTO>> listMembers() {
        List<MemberDTO> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{memberId}/lectures")
    public ResponseEntity<List<LectureDTO>> getMemberLectures(@PathVariable Long memberId) {
        List<LectureDTO> lectures = memberService.getEnrolledLectures(memberId);
        return ResponseEntity.ok(lectures);
    }
}
