package com.thwh.buls_own_shop.controller;

import com.thwh.buls_own_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

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
