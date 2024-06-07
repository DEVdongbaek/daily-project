package com.HelloWorld.Daily.controller;

import com.HelloWorld.Daily.dto.MemberDTO;
import com.HelloWorld.Daily.entity.Member;
import com.HelloWorld.Daily.jwtConfig.JwtToken;
import com.HelloWorld.Daily.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String signupForm(@ModelAttribute MemberDTO.RequestDTO requestDTO, Model model) {

        memberService.saveMember(requestDTO);

        return "index";
    }

    // 로그인 - GET
    @RequestMapping("/login")
    public String loginForm(@ModelAttribute MemberDTO.RequestDTO requestDTO, Model model) {

        model.addAttribute(requestDTO);

        return "login";
    }
}
