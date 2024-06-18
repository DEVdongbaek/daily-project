package com.HelloWorld.Daily.controller;

import com.HelloWorld.Daily.dto.DailyDTO;
import com.HelloWorld.Daily.service.DailyLikeService;
import com.HelloWorld.Daily.service.DailyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DailyRestController {

    private final DailyService dailyService;

    private final DailyLikeService dailyLikeService;

    // 홈에서 띄워지는 Daily
    // Paging이 있어야 함.
    @GetMapping("/dailies/{startId}")
    public ResponseEntity<DailyDTO.ResponseDTOs> getDailies(@AuthenticationPrincipal UserDetails userDetails, @PathVariable int startId){
        return new ResponseEntity<>(dailyService.getDailies(userDetails.getUsername(), startId), HttpStatus.OK);
    }

    // TODO : 클릭시 보여지는 디테일 페이지
    @GetMapping("/daily")
    public void getDailies(){

    }

    // Daily 생성
    @PostMapping("/daily")
    public void postDailies(@AuthenticationPrincipal UserDetails userDetails, @RequestBody DailyDTO.RequestDTO requestDTO){
        dailyService.saveDaily(userDetails.getUsername(), requestDTO);
    }

    // 좋아요 기능
    @PostMapping("/dailyLike/{dailyId}")
    public ResponseEntity<Boolean> doDailyLike(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long dailyId){
        return new ResponseEntity<>(dailyLikeService.doDailyLike(userDetails.getUsername(), dailyId), HttpStatus.OK);
    }
}
