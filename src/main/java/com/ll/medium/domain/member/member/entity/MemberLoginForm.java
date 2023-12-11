package com.ll.medium.domain.member.member.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginForm {
    // TODO 자바스크립트의 경고메세지와 통일
    @NotEmpty(message = "사용자ID는 필수항목입니다.")
    private String username;
    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password;
}
