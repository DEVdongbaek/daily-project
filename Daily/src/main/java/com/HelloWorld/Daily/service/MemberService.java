package com.HelloWorld.Daily.service;

import com.HelloWorld.Daily.dto.MemberDTO;
import com.HelloWorld.Daily.entity.Level;
import com.HelloWorld.Daily.entity.Member;
import com.HelloWorld.Daily.repository.LevelRepository;
import com.HelloWorld.Daily.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final LevelRepository levelRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void saveMember(UserDetails userDetails, MemberDTO.RequestDTO requestDTO){
        //코드에 하자가 너무 많습니다(compile error로 도배가 되어 있네요)... 톰캣 설정 전에 자체 에러 뜨네요..
        // TODO 이미 같은 userName이 있는 Member가 없는지 검증
        try {
            if (memberRepository.findByUserName(userDetails.getUsername()).isPresent()) {
                throw new Exception("이미 존재하는 아이디 입니다!");
            }
        }catch(Exception e){
            System.out.println("error : "+e.getMessage());// 사이트에서 오류 메세지 띠우는 코드가 이게 맞는지 모르겠네요?
        }
        // TODO Nickname도 검증
        try {
            if(memberRepository.findByUserNickName(requestDTO.getUserNickname()).isPresent()){
                throw new Exception("같은 닉네임이 존재합니다.");
            }
        }catch(Exception e){
            System.out.println("error : "+e.getMessage());// 사이트에서 오류 메세지 띠우는 코드가 이게 맞는지 모르겠네요?
        }

        Member memberEntity = memberRepository.save(Member.of(requestDTO));

        Member member = setInfoMember(memberEntity);

        Level level = levelRepository.save(Level.of(member));

        member.setLevel(level);
    }

    private Member setInfoMember(Member member){
        member.encodePassword(passwordEncoder);

        member.addRole("USER");

        return member;
    }
}



