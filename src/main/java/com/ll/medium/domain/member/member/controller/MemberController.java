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
    public String join(@Valid MemberJoinForm memberJoinForm) {
        // TODO 중복 아이디 방지 로직을 join 메소드로 옮기기 ( sb-2023-11-14 참고)
        // TODO toastr 적용 방법 찾기

        RsData<Member> joinRs = memberService.join(
                memberJoinForm.getUsername(),
                memberJoinForm.getPassword(),
                memberJoinForm.getPasswordConfirm()
        );
        return rq.redirectOrBack("/member/login", joinRs);
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
