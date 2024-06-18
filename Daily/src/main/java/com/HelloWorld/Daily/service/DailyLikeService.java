package com.HelloWorld.Daily.service;

import com.HelloWorld.Daily.entity.DailyLike;
import com.HelloWorld.Daily.repository.DailyLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DailyLikeService {

    private final DailyLikeRepository dailyLikeRepository;

    @Transactional
    public boolean doDailyLike(String userName, Long dailyId) {

        DailyLike dailyLike = dailyLikeRepository.selectDailyLikeByDaily(dailyId);

        if (dailyLike.isClickedLike(userName)) {
            dailyLike.disLike(userName);
            return false;
        }

        if (!dailyLike.isClickedLike(userName)) {
            dailyLike.addLike(userName);
            return true;
        }

        return false;
    }
}
