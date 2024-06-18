package com.HelloWorld.Daily.service;

import com.HelloWorld.Daily.dto.DailyDTO;
import com.HelloWorld.Daily.entity.Daily;
import com.HelloWorld.Daily.repository.DailyContentRepository;
import com.HelloWorld.Daily.repository.DailyLikeRepository;
import com.HelloWorld.Daily.repository.DailyRepository;
import com.HelloWorld.Daily.utils.MockUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DailyServiceTest {

    @InjectMocks
    private DailyService dailyService;

    @Mock
    private DailyRepository dailyRepository;

    @Mock
    private DailyLikeRepository dailyLikeRepository;

    @Mock
    private DailyContentRepository dailyContentRepository;

    @Test
    public void get_dailies() {
        // given
        List<Daily> dailies = MockUtils.getDailyList();

        // mocking
        given(dailyRepository.findDailiesWithOffsetAndLimit(PageRequest.of(0, 5)))
                .willReturn(dailies);

        given(dailyLikeRepository.selectDailyLikeByDaily(any()))
                .willReturn(MockUtils.getDailyLike());

        given(dailyContentRepository.selectDailyContentByDaily(any()))
                .willReturn(MockUtils.getDailyContent());

        // when
        DailyDTO.ResponseDTOs dtos = dailyService.getDailies(MockUtils.memberName, 0);

        // then
        assertThat(dtos.getResponseDTOS()).isNotNull();
        assertThat(dtos.getResponseDTOS().size()).isEqualTo(3);
        assertThat(dtos.getResponseDTOS().get(0).getThanks1()).isEqualTo("감사1");

    }
}
