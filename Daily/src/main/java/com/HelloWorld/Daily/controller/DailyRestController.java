package com.HelloWorld.Daily.controller;

import com.HelloWorld.Daily.common.ApiResponse;
import com.HelloWorld.Daily.dto.DailyDTO;
import com.HelloWorld.Daily.service.DailyLikeService;
import com.HelloWorld.Daily.service.DailyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DailyRestController {

    private final DailyService dailyService;

    private final DailyLikeService dailyLikeService;

    // 홈에서 띄워지는 Daily
    @GetMapping("/dailies/{offset}/{limit}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<DailyDTO.ResponseDTOs> getDailies(@AuthenticationPrincipal UserDetails userDetails, @PathVariable int offset, @PathVariable int limit){

        if (userDetails == null) {
            return ApiResponse.createSuccess(dailyService.getDailies("anonymous", offset, limit));
        }

        return ApiResponse.createSuccess(dailyService.getDailies(userDetails.getUsername(), offset, limit));
    }

    // 홈에서 띄워지는 Daily
    @GetMapping("/my/dailies/{offset}/{limit}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<DailyDTO.ResponseDTOs> getMyDailies(@AuthenticationPrincipal UserDetails userDetails, @PathVariable int offset, @PathVariable int limit){

        if (userDetails == null) {
            return ApiResponse.createSuccess(dailyService.getMyDailies("anonymous", offset, limit));
        }

        return ApiResponse.createSuccess(dailyService.getMyDailies(userDetails.getUsername(), offset, limit));
    }

    // Daily 생성
    @PostMapping("/daily")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<?> postDailies(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody DailyDTO.RequestDTO requestDTO){

        dailyService.saveDaily(userDetails, requestDTO);

        return ApiResponse.createSuccessWithNoContent(); // 공통 API를 반환하기 위한 ApiResponse 객체 사용
    }

    // Daily 수정
    @PutMapping("/daily/{dailyId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> updateDaily(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long dailyId, @Valid @RequestBody DailyDTO.RequestDTO requestDTO) {

        dailyService.updateDaily(userDetails, dailyId, requestDTO);

        return ApiResponse.createSuccessWithNoContent(); // 공통 API를 반환하기 위한 ApiResponse 객체 사용
    }

    // Daily 삭제
    @PostMapping("/daily-delete/{dailyId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> deleteDaily(@PathVariable Long dailyId) {

        dailyService.deleteDaily(dailyId);

        return ApiResponse.createSuccessWithNoContent(); // 공통 API를 반환하기 위한 ApiResponse 객체 사용
    }

    // 좋아요 기능
    @PostMapping("/daily-like/{dailyId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> doDailyLike(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long dailyId){

        return ApiResponse.createSuccess(dailyLikeService.doDailyLike(userDetails, dailyId));
    }
}
