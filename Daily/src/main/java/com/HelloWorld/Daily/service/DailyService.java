package com.HelloWorld.Daily.service;

import com.HelloWorld.Daily.dto.DailyDTO;
import com.HelloWorld.Daily.entity.Daily;
import com.HelloWorld.Daily.entity.DailyContent;
import com.HelloWorld.Daily.entity.DailyLike;
import com.HelloWorld.Daily.entity.Member;
import com.HelloWorld.Daily.repository.DailyContentRepository;
import com.HelloWorld.Daily.repository.DailyLikeRepository;
import com.HelloWorld.Daily.repository.DailyRepository;
import com.HelloWorld.Daily.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DailyService {

    private final DailyRepository dailyRepository;

    private final MemberRepository memberRepository;

    private final DailyLikeRepository dailyLikeRepository;

    private final DailyContentRepository dailyContentRepository;

    @Transactional(readOnly = true)
    public DailyDTO.ResponseDTOs getDailies(String memberName, int offset, int limit){

        List<Daily> dailies = getDailyList(offset, limit);

        System.out.println("Daily Size : "  + dailies.size());

        return DailyDTO.ResponseDTOs.of(
                dailies.stream().map(
                daily -> getResponseDTO(daily, memberName)
                ).toList()
        );
    }

    @Transactional
    public void saveDaily(String memberName, DailyDTO.RequestDTO requestDTO){

        // 해당 유저가 하루 내로 작성한 글이 있는지 확인 -> Exception 반환
        if (dailyRepository.findDailyInDay(memberName).isPresent()){
            throw new RuntimeException("하루 내로 작성한 글이 이미 있습니다.");
        }

        Optional<Member> member = memberRepository.findByUserName(memberName);

        member.ifPresent(value -> saveEntityAboutDaily(value, requestDTO));

    }

    // Daily 관련 Entity save
    private void saveEntityAboutDaily(Member member, DailyDTO.RequestDTO requestDTO){
        Daily daily = dailyRepository.save(Daily.of(requestDTO, member));
        dailyLikeRepository.save(DailyLike.of(daily));
        dailyContentRepository.save(DailyContent.of(daily, requestDTO));
    }


    // ResponseDTO 조회 및 객체화
    private DailyDTO.ResponseDTO getResponseDTO(Daily daily, String memberName){
        return DailyDTO.ResponseDTO.of(
                daily,
                dailyLikeRepository.selectDailyLikeByDaily(daily.getId()),
                dailyContentRepository.selectDailyContentByDaily(daily.getId()),
                memberName
        );
    }

    // Daily 조회
    private List<Daily> getDailyList(int offset, int limit) {
        return dailyRepository.findDailiesWithOffsetAndLimit(PageRequest.of(offset, limit));
    }
}
