package com.HelloWorld.Daily.repository;

import com.HelloWorld.Daily.entity.Daily;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DailyRepository extends JpaRepository<Daily, Long> {

    // 하루 내로 작성된 Daily가 있는지 확인
    @Query(value = "SELECT d " +
            "FROM Daily d " +
            "WHERE d.member.userName = :memberName " +
            "AND month(d.createdAt) = month(CURRENT_TIMESTAMP)")
    Optional<Daily> findDailyInDay(@Param("memberName") String memberName);

    @Query("SELECT d " +
            "FROM Daily d " +
            "WHERE d.isPublic = true " +
            "ORDER BY d.id DESC")
    List<Daily> findDailiesWithOffsetAndLimit(Pageable pageable);

    @Query("SELECT d " +
            "FROM Daily d " +
            "WHERE d.member.userName = :memberName " +
            "ORDER BY d.id DESC")
    List<Daily> findMyDailiesWithOffsetAndLimit(@Param("memberName") String memberName, Pageable pageable);

}
