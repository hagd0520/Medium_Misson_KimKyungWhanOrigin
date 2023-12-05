package com.ll.medium.domain.member.member.entity;

import com.ll.medium.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class Member {
    private final MemberService memberService;
}
