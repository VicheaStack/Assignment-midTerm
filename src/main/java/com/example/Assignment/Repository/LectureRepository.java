package com.example.Assignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Assignment.Entity.Lectures;

@Repository
public interface LectureRepository extends JpaRepository<Lectures, Long> {

}
