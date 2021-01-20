package com.thwh.buls_own_shop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class LoginForm {
    @NotEmpty(message = "ID 입력은 필수입니다")
    private String id;

    @NotEmpty(message = "비밀번호 입력은 필수입니다")
    private String pw;
}
