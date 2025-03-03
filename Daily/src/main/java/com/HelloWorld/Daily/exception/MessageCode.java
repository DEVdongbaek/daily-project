package com.HelloWorld.Daily.exception;

import lombok.Getter;

@Getter
public enum MessageCode {

    DOES_NOT_EXIST_MEMBER("해당 멤버가 존재하지 않습니다."),

    DOES_NOT_EXIST_DAILY("해당 Daily가 존재하지 않습니다."),

    WRITTEN_DAILY_IN_A_DAY("오늘 이미 Daily를 작성하였습니다.");

    // 메시지를 반환하는 메서드
    private final String message;

    // Enum 생성자
    MessageCode(String message) {
        this.message = message;
    }

}
