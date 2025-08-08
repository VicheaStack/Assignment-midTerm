package com.example.Assignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Assignment.Entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
