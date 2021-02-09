package com.thwh.buls_own_shop.controller;

import com.thwh.buls_own_shop.domain.Address;
import com.thwh.buls_own_shop.domain.Member;
import com.thwh.buls_own_shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/newMember";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result) {
        if (result.hasErrors()) {
            return "members/newMember";
        }

        memberService.signUp(memberForm);   // MemberService 로 memberForm 객체를 넘겨서 거기서 입력받은 변수들을 받아와 가입 진행
        return "redirect:/";
    }


    @GetMapping("/members/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "members/login";
    }

    @PostMapping("/members/login")
    public String login(@Valid LoginForm loginForm, BindingResult result) {
        if (result.hasErrors()) {
            return "members/login";
        }


        memberService.signIn(loginForm);
        return "redirect:/";
    }


}
