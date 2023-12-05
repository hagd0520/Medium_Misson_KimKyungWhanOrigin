package com.ll.medium.domain.member.member.controller;

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
        return null;
    }

}
