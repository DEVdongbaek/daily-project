package com.HelloWorld.Daily.dto;

import com.HelloWorld.Daily.entity.Daily;
import com.HelloWorld.Daily.entity.DailyContent;
import com.HelloWorld.Daily.entity.DailyLike;
import lombok.*;

import java.util.List;

public class DailyDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestDTO {

        private boolean itIsPublic;

        private String thanks1;

        private String thanks2;

        private String thanks3;

        private String penitence1;

        private String penitence2;

        private String penitence3;

    }

    @Getter
    @Builder
    public static class ResponseDTOs {

        private List<ResponseDTO> responseDTOS;

        public static ResponseDTOs of(List<ResponseDTO> responseDTOS) {
            return ResponseDTOs.builder()
                    .responseDTOS(responseDTOS)
                    .build();
        }

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseDTO {

        private Long dailyId;

        private int dailyLikeCnt;

        private boolean isClickedLike;

        private boolean isPublic;

        private String thanks1;

        private String thanks2;

        private String thanks3;

        private String penitence1;

        private String penitence2;

        private String penitence3;

        public static ResponseDTO of(Daily daily, DailyLike dailyLike, DailyContent dailyContent, String userName){
            return ResponseDTO.builder()
                    .dailyId(daily.getId())
                    .dailyLikeCnt(dailyLike.getUsersCount())
                    .isClickedLike(dailyLike.isClickedLike(userName))
                    .isPublic(daily.isPublic())
                    .thanks1(dailyContent.getThanks1())
                    .thanks2(dailyContent.getThanks2())
                    .thanks3(dailyContent.getThanks3())
                    .penitence1(dailyContent.getPenitence1())
                    .penitence2(dailyContent.getPenitence2())
                    .penitence3(dailyContent.getPenitence3())
                    .build();
        }
    }

}
