package com.example.Assignment.Entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String memberName;

	@Column(name = "age")
	private int age;

	@Column(name = "address")
	private String address;

	@CreatedDate
	@Column(name = "created_date", updatable = false)
	private LocalDateTime creationDate;

	@ManyToOne
	@JoinColumn(name = "team_id", nullable = false)
	private Team team;

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<MemberLecture> registration; // <-- Changed from List<Lectures> to List<MemberLecture>
}
