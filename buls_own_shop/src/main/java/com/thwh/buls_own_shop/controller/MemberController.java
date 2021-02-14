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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerTypePredicate;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        memberService.rootDefault();    // root 아이디 자동생성(이미 있으면 더 생성 안함)
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
        memberService.rootDefault();    // root 아이디 자동생성(이미 있으면 더 생성 안함)
        if (result.hasErrors()) {
            return "members/login";
        }else {
            List<Member> ids = memberRepository.findById(id);
            Member member = ids.get(0);
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

    @GetMapping("/members/{id}/myPage")
    public String myPage(@PathVariable("id") Long id, Model model) {
        Member member = memberService.findOne(id);
        model.addAttribute("member", member);
        return "/myPage/myPage";
    }
    @GetMapping("/members/myPage")
    public String myPage_main(){ return "myPage/myPage";}

    @RequestMapping("/members/myPage")
    public String myPage(){
        return "myPage/myPage";
    }
}