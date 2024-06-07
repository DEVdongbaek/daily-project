package com.HelloWorld.Daily.jwtConfig;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtToken {

    private String grantType;

    private String accessToken;

}
