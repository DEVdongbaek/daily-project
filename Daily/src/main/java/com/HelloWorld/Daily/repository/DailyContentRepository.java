package com.HelloWorld.Daily.repository;

import com.HelloWorld.Daily.entity.DailyContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DailyContentRepository extends JpaRepository<DailyContent, Long> {

    @Query("select dt " +
            "from DailyContent dt " +
            "where dt.daily.id = :dailyId")
    DailyContent selectDailyContentByDaily(Long dailyId);

}
