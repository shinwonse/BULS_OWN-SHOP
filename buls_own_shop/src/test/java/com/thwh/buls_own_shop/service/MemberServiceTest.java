package com.thwh.buls_own_shop.service;

import com.thwh.buls_own_shop.controller.LoginForm;
import com.thwh.buls_own_shop.controller.MemberForm;
import com.thwh.buls_own_shop.domain.Member;
import com.thwh.buls_own_shop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;


    @Test
    public void 로그인_존재하지않는아이디_테스트(){
        MemberForm memberForm = new MemberForm();
        memberForm.setUser_id("abc");
        memberForm.setName("A");
        memberForm.setPw("1234");
        memberForm.setBirth("990101");

        LoginForm loginForm = new LoginForm();
        loginForm.setId("abcㅇ");
        loginForm.setPw("1234");

        memberService.signUp(memberForm);
        try {
            memberService.signIn(loginForm);
        } catch (IllegalStateException e) {
            return;
        }

        fail("존재하지 않는 아이디 예외가 발생해야 함");
    }

    @Test
    public void 로그인_틀린비밀번호_테스트() {
        MemberForm memberForm = new MemberForm();
        memberForm.setUser_id("abc");
        memberForm.setName("A");
        memberForm.setPw("1234");
        memberForm.setBirth("990101");

        LoginForm loginForm = new LoginForm();
        loginForm.setId("abc");
        loginForm.setPw("123");

        memberService.signUp(memberForm);
        try {
            memberService.signIn(loginForm);
        } catch (IllegalStateException e) {
            return;
        }

        fail("틀린 비밀번호 예외가 발생해야 함");
    }
}