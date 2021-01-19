package com.thwh.buls_own_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/admin")
    public String adminMain() {
        return "administrator/admin";
    }

    @GetMapping("/admin/newItem")
    public String createItemForm(Model model) {
        return "administrator/createItemForm";
    }

    @GetMapping("/admin/itemList")
    public String itemList(Model model) {
        return "administrator/itemList";
    }

    @GetMapping("/admin/memberList")
    public String memberList(Model model) {
        return "administrator/memberList";
    }
}
