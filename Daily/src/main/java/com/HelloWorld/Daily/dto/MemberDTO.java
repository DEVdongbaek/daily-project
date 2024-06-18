package com.HelloWorld.Daily.dto;

import com.HelloWorld.Daily.entity.Member;
import lombok.*;

@Getter
public class MemberDTO {

    @Getter
    @Builder
    public static class RequestDTO {

        private String userName;

        private String userPassword;

        private String userNickname;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseDTO {

        private String userName;

        private String userNickname;

        public static ResponseDTO of(Member member) {
            return ResponseDTO.builder()
                    .userName(member.getUsername())
                    .userNickname(member.getNickName())
                    .build();
        }
    }

}
