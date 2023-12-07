package com.ll.medium.domain.member.member.entity;

import lombok.Data;

@Data
public class MemberJoinForm {
    private String username;
    private String password;
    private String passwordConfirm;
}
