package com.HelloWorld.Daily.repository;

import com.HelloWorld.Daily.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m " +
            "from Member m " +
            "join fetch m.level " +
            "where m.userName = :userName")
    Optional<Member> findByUserName(String userName);

    @Query("select m " +
            "from Member m " +
            "join fetch m.level " +
            "where m.userNickname = :userNickname")
    Optional<Member> findByUserNickName(String userNickname);
}
