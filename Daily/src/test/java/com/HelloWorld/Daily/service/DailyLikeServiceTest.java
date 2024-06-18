package com.HelloWorld.Daily.service;

import com.HelloWorld.Daily.entity.DailyLike;
import com.HelloWorld.Daily.repository.DailyLikeRepository;
import com.HelloWorld.Daily.utils.MockUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DailyLikeServiceTest {

    @InjectMocks
    private DailyLikeService dailyLikeService;

    @Mock
    private DailyLikeRepository dailyLikeRepository;

    @Test
    public void get_daily_like() {
        // given
        DailyLike dailyLike = MockUtils.getDailyLike();

        // mocking
        given(dailyLikeRepository.selectDailyLikeByDaily(any()))
                .willReturn(dailyLike);

        // when
        boolean addLike = dailyLikeService.doDailyLike("test", 1L);

        boolean removeLike = dailyLikeService.doDailyLike("test", 1L);

        // then
        assertThat(addLike).isTrue();
        assertThat(removeLike).isFalse();
    }
}
