package com.HelloWorld.Daily.entity;

import com.HelloWorld.Daily.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends Common {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String password;

    private String nickName;

    public static User of(UserDTO.RequestDTO userDto){
        return User.builder()
                .userName(userDto.getUserName())
                .password(userDto.getUserPassword())
                .nickName(userDto.getUserNickname())
                .build();
    }
}
