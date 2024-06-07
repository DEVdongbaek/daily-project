package com.HelloWorld.Daily.repository;

import com.HelloWorld.Daily.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserName(String userName);

}
