package com.HelloWorld.Daily.controller;

import com.HelloWorld.Daily.dto.MemberDTO;
import com.HelloWorld.Daily.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);

    private final MemberService memberService;

    // 홈
    @GetMapping("/")
    public String home(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("nickname", authentication.getName());

        return "index";

    }

    // 회원가입 - GET
    @GetMapping("/signup")
    public String signup(@ModelAttribute MemberDTO.RequestDTO requestDTO, Model model) {

        model.addAttribute(requestDTO);

        return "signup";
    }

    // 회원가입 - POST
    @PostMapping("/signup")
    public String signupForm(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid MemberDTO.RequestDTO requestDTO, Model model) {

        memberService.saveMember(userDetails, requestDTO);

        return "redirect:login";
    }

    // 로그인 - GET
    @RequestMapping("/login")
    public String login(@ModelAttribute MemberDTO.RequestDTO requestDTO, Model model) {

        if (requestDTO.getUserName() == null) {

            model.addAttribute(requestDTO);

            return "login";
        }

        return "redirect:index";
    }
}
