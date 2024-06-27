package com.HelloWorld.Daily.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class DailyController {

    @RequestMapping("my-page")
    public String getMyPage(){
        return "myPage.html";
    }

}
