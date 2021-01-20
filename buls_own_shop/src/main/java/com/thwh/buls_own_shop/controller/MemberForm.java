package com.thwh.buls_own_shop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {
    @NotEmpty(message = "ID 입력은 필수입니다")
    private String user_id;

    @NotEmpty(message = "비밀번호 입력은 필수입니다")
    private String pw;

    @NotEmpty(message = "이름 입력은 필수입니다")
    private String name;
    
    @NotEmpty(message = "생년월일 입력은 필수입니다")
    private String birth;
    
    @NotEmpty(message = "번호 입력은 필수입니다")
    private String phoneNumber;
    
    private String city;
    private String street;
}
