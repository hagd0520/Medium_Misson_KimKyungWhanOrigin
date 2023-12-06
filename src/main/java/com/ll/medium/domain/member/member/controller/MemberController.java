package com.ll.medium.domain.member.member.controller;

import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.domain.member.member.entity.MemberJoinForm;
import com.ll.medium.domain.member.member.service.MemberService;
import com.ll.medium.global.rq.Rq;
import com.ll.medium.global.rsData.RsData;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final Rq rq;

    @GetMapping("/join")
    public String showJoin() {
        return "domain/member/member/join";
    }

    @PostMapping("/join")
    public String join(@Valid MemberJoinForm memberJoinForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/join";
        }

        if (!memberJoinForm.getPassword().equals(memberJoinForm.getPasswordConfirm())) {
            return rq.historyBack("비밀번호가 일치하지 않습니다.");
        }

        memberService.findByUsername(memberJoinForm.getUsername());

        RsData<Member> joinRs = memberService.join(memberJoinForm.getUsername(), memberJoinForm.getPassword());
        return rq.redirect("/member/login", joinRs);
    }

    @GetMapping("/login")
    public String showLogin() {
        return "domain/member/member/login";
    }

    @Getter
    @RequiredArgsConstructor
    public static class LoginForm {
        private final String username;
        private final String password;
    }

    @PostMapping("/login")
    public String login(LoginForm loginForm) {
        return null;
    }
}
