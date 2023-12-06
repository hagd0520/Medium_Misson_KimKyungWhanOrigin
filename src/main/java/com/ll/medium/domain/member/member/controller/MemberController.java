package com.ll.medium.domain.member.member.controller;

import com.ll.medium.domain.member.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Getter
    @RequiredArgsConstructor
    public static class JoinForm {
        private final String username;
        private final String password;
        private final String passwordConfirm;
    }

    @GetMapping("/join")
    public String showJoin() {
        return "domain/member/member/join";
    }

    @PostMapping("/join")
    public String doJoin(JoinForm joinForm) {
        memberService.join(joinForm.getUsername(), joinForm.getPassword());
        return null;
    }
}
