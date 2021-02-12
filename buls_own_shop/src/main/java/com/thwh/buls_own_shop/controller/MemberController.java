package com.thwh.buls_own_shop.controller;

import com.thwh.buls_own_shop.domain.Member;
import com.thwh.buls_own_shop.repository.MemberRepository;
import com.thwh.buls_own_shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Address;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerTypePredicate;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

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
    public String login(@Valid HttpSession session, LoginForm loginForm, BindingResult result, String id, String pw) {
        if (result.hasErrors()) {
            return "members/login";
        }else {
            Member member = memberRepository.findOne(id);
            session.setAttribute("member", member);
            memberService.signIn(loginForm);
            return "redirect:/";
        }
    }
    @GetMapping("/members/logout")
    public String logout(HttpSession session){
        session.removeAttribute("member");
        return "redirect:/";
    }
    @RequestMapping("/members/myPage")
    public String myPage(){

        return "myPage/myPage";
    }
}