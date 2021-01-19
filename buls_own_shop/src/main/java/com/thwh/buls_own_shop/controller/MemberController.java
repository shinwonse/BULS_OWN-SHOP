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

        Address address = new Address(memberForm.getCity(), memberForm.getStreet());
        Member member = new Member();
        member.setUser_id(memberForm.getUser_id());
        member.setName(memberForm.getName());
        member.setBirth(memberForm.getBirth());
        member.setPhoneNumber(memberForm.getPhoneNumber());
        member.setAddress(address);

        memberService.signUp(member);
        return "redirect:/";
    }


    @GetMapping("/members/login")
    public String loginForm(Model model) {
        return "members/login";
    }
}
