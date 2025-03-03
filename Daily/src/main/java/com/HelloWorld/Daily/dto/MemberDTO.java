package com.HelloWorld.Daily.dto;

import com.HelloWorld.Daily.entity.Member;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
public class MemberDTO {

    @Getter
    @Builder
    public static class RequestDTO implements com.HelloWorld.Daily.dto.RequestDTO {

        @Length(min = 6, max = 15, message = "ID는 최소 6글자, 최대 15 글자 이하로 작성하여야 합니다.")
        private String userName;

        @Length(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하의 길이여야 합니다.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$", message = "비밀번호는 문자와 숫자가 혼용되어야 합니다.")
        private String userPassword;

        @Length(min = 6, max = 15, message = "닉네임은 최소 6글자, 최대 15 글자 이하로 작성하여야 합니다.")
        private String userNickname;

        public void setUserNickname(String userNickname){
            this.userNickname = userNickname;
        }

        public String getUserNickname() {
            return userNickname;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }
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
