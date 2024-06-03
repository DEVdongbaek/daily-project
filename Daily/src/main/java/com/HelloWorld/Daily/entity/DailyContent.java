package com.HelloWorld.Daily.entity;

import com.HelloWorld.Daily.dto.DailyDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DailyContent extends Common {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Daily daily;

    private String thanks1;

    private String thanks2;

    private String thanks3;

    private String penitence1;

    private String penitence2;

    private String penitence3;

    public static DailyContent of(Daily daily, DailyDTO.RequestDTO requestDTO){
        return DailyContent.builder()
                .daily(daily)
                .thanks1(requestDTO.getThanks1())
                .thanks2(requestDTO.getThanks2())
                .thanks3(requestDTO.getThanks3())
                .penitence1(requestDTO.getPenitence1())
                .penitence2(requestDTO.getPenitence2())
                .penitence3(requestDTO.getPenitence3())
                .build();
    }
}
