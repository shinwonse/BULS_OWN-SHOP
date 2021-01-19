package com.thwh.buls_own_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/product/glove")
    public String glove_list(Model model) {
        return "product/glove/glove_main";
    }

    @GetMapping("/product/bat")
    public String bat_list(Model model) {
        return "product/bat/bat_main";
    }

    @GetMapping("/product/spike")
    public String spike_list(Model model) {
        return "product/spike/spike_main";
    }
}
