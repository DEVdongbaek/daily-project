package com.HelloWorld.Daily.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DailyLike extends Common {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private List<String> members = new ArrayList<>(); // 해당 필드로 좋아요 수 체크

    @OneToOne(fetch = FetchType.LAZY)
    private Daily daily;

    public static DailyLike of(Daily daily) {
        return DailyLike.builder()
                .daily(daily)
                .build();
    }

    public boolean isClickedLike(String userName) {
        if (this.members.contains(userName)) {
            return true;
        }
        return false;
    }
    public void addLike(String userName) {
        this.members.add(userName);
    }

    public void disLike(String userName) {
        this.members.remove(userName);
    }

    public int getUsersCount() {
        return this.members.size();
    }
}
