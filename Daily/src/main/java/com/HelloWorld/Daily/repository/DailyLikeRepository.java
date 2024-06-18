package com.HelloWorld.Daily.repository;

import com.HelloWorld.Daily.entity.DailyLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DailyLikeRepository extends JpaRepository<DailyLike, Long> {

    @Query("select dl " +
            "from DailyLike dl " +
            "where dl.daily.id = :dailyId")
    DailyLike selectDailyLikeByDaily(Long dailyId);

}
