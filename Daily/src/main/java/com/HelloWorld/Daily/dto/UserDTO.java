package com.HelloWorld.Daily.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDTO {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class RequestDTO {

        private String userName;

        private String userPassword;

        private String userNickname;

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ResponseDTO {

        private String userName;

        private String userPassword;

        private String userNickname;

    }

}
