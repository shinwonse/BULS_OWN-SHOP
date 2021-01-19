package com.thwh.buls_own_shop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {
    @NotEmpty(message = "회원 이름은 필수입니다")
    private String name;

    private String age;
    private String phoneNumber;
    private String address;
}
