package com.HelloWorld.Daily.entity;

import com.HelloWorld.Daily.dto.DailyDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
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

    public void updateDailyContent(DailyDTO.RequestDTO requestDTO) {
        if (requestDTO.getThanks1() != null) {
            this.thanks1 = requestDTO.getThanks1();
        }
        if (requestDTO.getThanks2() != null) {
            this.thanks2 = requestDTO.getThanks2();
        }
        if (requestDTO.getThanks3() != null) {
            this.thanks3 = requestDTO.getThanks3();
        }
        if (requestDTO.getPenitence1() != null) {
            this.penitence1 = requestDTO.getPenitence1();
        }
        if (requestDTO.getPenitence2() != null) {
            this.penitence2 = requestDTO.getPenitence2();
        }
        if (requestDTO.getPenitence3() != null) {
            this.penitence3 = requestDTO.getPenitence3();
        }
    }

    public void deleteDailyContent(DailyDTO.RequestDTO requestDTO) {
        this.thanks1 = null;
        this.thanks2 = null;
        this.thanks3 = null;
        this.penitence1 = null;
        this.penitence2 = null;
        this.penitence3 = null;
    }
}
