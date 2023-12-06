package com.ll.medium.domain.member.member.service;

import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.domain.member.member.repository.MemberRepository;
import com.ll.medium.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public RsData<Member> join(String username, String password) {
        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();

        return RsData.of("200",
                "성공",
                memberRepository.save(member));
    }
}
