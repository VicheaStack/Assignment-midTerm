package com.example.Assignment.Entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@Entity(name = "lecture")
public class Lectures {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@Column(nullable = false)
	private String content;

	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;

	@OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
	private List<MemberLecture> registration;

	@PrePersist
	protected void onCreate() {
		this.createdDate = LocalDateTime.now();
	}
}
