package com.HelloWorld.Daily.service;

import com.HelloWorld.Daily.dto.MemberDTO;
import com.HelloWorld.Daily.entity.Member;
import com.HelloWorld.Daily.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberDTO.ResponseDTO saveMember(MemberDTO.RequestDTO requestDTO){

        // TODO 이미 같은 userName이 있는 Member가 없는지 검증
        // TODO Nickname도 검증

        Member member = Member.of(requestDTO);

        member.encodePassword(passwordEncoder);

        member.addRole("USER");

        return MemberDTO.ResponseDTO.of(memberRepository.save(member));
    }
}



