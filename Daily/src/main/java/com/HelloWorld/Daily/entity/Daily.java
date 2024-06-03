package com.HelloWorld.Daily.entity;

import com.HelloWorld.Daily.dto.DailyDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Daily extends Common {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isPublic;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public static Daily of(DailyDTO.RequestDTO dailyDTO) {
        return Daily.builder()
                .isPublic(dailyDTO.isPublic())
                .build();
    }

}
