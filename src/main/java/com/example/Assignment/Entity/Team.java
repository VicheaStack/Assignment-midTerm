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
@Entity(name = "teams")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "teamName")
	private String name;

	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private List<Member> members;

	@PrePersist
	public void prePersist() {
		if (createdDate == null) {
			createdDate = LocalDateTime.now();
		}
	}
}
