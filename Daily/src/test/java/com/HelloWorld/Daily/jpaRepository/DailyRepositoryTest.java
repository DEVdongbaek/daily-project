package com.HelloWorld.Daily.jpaRepository;

import com.HelloWorld.Daily.entity.Daily;
import com.HelloWorld.Daily.entity.Member;
import com.HelloWorld.Daily.repository.DailyRepository;
import com.HelloWorld.Daily.repository.MemberRepository;
import com.HelloWorld.Daily.utils.MockUtils;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DailyRepositoryTest {

    @Autowired
    private DailyRepository dailyRepository;

    @Autowired
    private MemberRepository memberRepository;

    private static Member member;

    private static Daily daily;

    @BeforeEach // 테스트 실행 전 실행
    @Transactional
    void setup(){
        System.err.println("=============================== setup ==============================");

        member = memberRepository.save(MockUtils.getMember());

        daily = dailyRepository.save(MockUtils.getPublicDaily(member));

    }

    @Test
    @DisplayName("하루 내에 작성된 Daily가 있는가?")
    @Transactional
    @Order(1)
    public void findDailyInDay(){

        // given
        // already given

        // when
        Optional<Daily> resultDaily = dailyRepository.findDailyInDay(MockUtils.memberName);

        // then
        assertThat(resultDaily.get()).isNotNull();
        assertThat(resultDaily.get().getMember().getUsername()).isEqualTo(MockUtils.memberName);
    }

    @Test
    @DisplayName("잘못된 유저 이름을 주었을 때 하루 내에 작성된 Daily가 있는가?")
    @Transactional
    @Order(2)
    public void findDailyInDayWithBadNameArg(){

        // given
        // already given

        // when
        Optional<Daily> resultDaily = dailyRepository.findDailyInDay("wrongName");

        // then
        assertThat(resultDaily.get()).isNotNull();
        assertThat(resultDaily.get().getMember().getUsername()).isEqualTo(MockUtils.memberName);
    }

    @Test
    @Order(3)
    @Transactional
    @DisplayName("Daily 3감사 3회개 조회")
    public void findDailiesWithOffsetAndLimit(){

        // given
        // already given

        // when
        List<Daily> resultDailies = dailyRepository.findDailiesWithOffsetAndLimit(PageRequest.of(0, 5));

        // then
        assertThat(resultDailies).isNotNull();
        assertThat(resultDailies.size()).isEqualTo(1);
        assertThat(resultDailies.get(0).getMember().getUsername()).isEqualTo(MockUtils.memberName);
    }
}

