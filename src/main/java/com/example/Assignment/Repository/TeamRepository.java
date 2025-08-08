package com.example.Assignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Assignment.Entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
