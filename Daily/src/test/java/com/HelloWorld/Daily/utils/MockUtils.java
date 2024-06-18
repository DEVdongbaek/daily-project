package com.HelloWorld.Daily.utils;

import com.HelloWorld.Daily.dto.DailyDTO;
import com.HelloWorld.Daily.dto.MemberDTO;
import com.HelloWorld.Daily.entity.Daily;
import com.HelloWorld.Daily.entity.DailyContent;
import com.HelloWorld.Daily.entity.DailyLike;
import com.HelloWorld.Daily.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class MockUtils {

    public static final String memberName = "test";


    public static Member getMember(){

        MemberDTO.RequestDTO memberRequestDTO = MemberDTO.RequestDTO.builder()
                .userName(memberName)
                .userNickname("nickname")
                .userPassword("12341234")
                .build();

        return Member.of(memberRequestDTO);

    }

    public static DailyDTO.RequestDTO getDailyRequestDTO(Member member){

        return DailyDTO.RequestDTO.builder()
                .isPublic(true)
                .thanks1("감사1")
                .thanks2("감사2")
                .thanks3("감사3")
                .penitence1("회개1")
                .penitence2("회개2")
                .penitence3("회개3")
                .build();

    }


    public static Daily getPublicDaily(Member member){

        DailyDTO.RequestDTO dailyRequestDTO = DailyDTO.RequestDTO.builder()
                .isPublic(true)
                .thanks1("감사1")
                .thanks2("감사2")
                .thanks3("감사3")
                .penitence1("회개1")
                .penitence2("회개2")
                .penitence3("회개3")
                .build();

        return Daily.of(dailyRequestDTO, member);

    }

    public static Daily getPrivateDaily(Member member){

        DailyDTO.RequestDTO privateDailyRequestDTO = DailyDTO.RequestDTO.builder()
                .isPublic(false)
                .thanks1("감사1")
                .thanks2("감사2")
                .thanks3("감사3")
                .penitence1("회개1")
                .penitence2("회개2")
                .penitence3("회개3")
                .build();

        return Daily.of(privateDailyRequestDTO, member);

    }

    public static DailyLike getDailyLike(){

        return DailyLike.of(getPublicDaily(getMember()));

    }

    public static DailyContent getDailyContent(){

        DailyDTO.RequestDTO dailyRequestDTO = DailyDTO.RequestDTO.builder()
                .isPublic(true)
                .thanks1("감사1")
                .thanks2("감사2")
                .thanks3("감사3")
                .penitence1("회개1")
                .penitence2("회개2")
                .penitence3("회개3")
                .build();

        return DailyContent.of(getPublicDaily(getMember()), dailyRequestDTO);

    }


     public static List<Daily> getDailyList() {
        List<Daily> dailies = new ArrayList<>();

        for (int i = 0; i < 3; i++){
            dailies.add(getPublicDaily(getMember()));
        }

        return dailies;
    }

}
