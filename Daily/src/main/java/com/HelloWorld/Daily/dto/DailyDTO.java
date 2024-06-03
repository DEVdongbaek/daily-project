package com.HelloWorld.Daily.dto;

import lombok.Getter;

public class DailyDTO {

    @Getter
    public static class RequestDTO {

        private boolean isPublic;

        private String thanks1;

        private String thanks2;

        private String thanks3;

        private String penitence1;

        private String penitence2;

        private String penitence3;

    }

    @Getter
    public static class ResponseDTO {

        private boolean isPublic;

    }

}
