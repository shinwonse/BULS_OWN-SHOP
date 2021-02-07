package com.thwh.buls_own_shop.controller;

import com.thwh.buls_own_shop.domain.product.Glove;
import com.thwh.buls_own_shop.domain.product.Product;
import com.thwh.buls_own_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/glove")
    public String glove_list(Model model) {
        Glove glove = new Glove();
        glove.setName("제트");

        List<Product> gloves = productService.findProducts();
        model.addAttribute("items", gloves);
        return "/product/glove/glove_list";
    }

    @GetMapping("/product/bat")
    public String bat_list(Model model) {
        List<Product> bats = productService.findProducts();
        model.addAttribute("items", bats);
        return "/product/bat/bat_list";
    }

    @GetMapping("/product/spike")
    public String spike_list(Model model) {
        List<Product> spikes = productService.findProducts();
        model.addAttribute("items", spikes);
        return "/product/spike/spike_list";
    }

//    @GetMapping("/product/glove/{glovdId}/buy")
//    public String glove_buy(@PathVariable("gloveId") Long gloveId, Model model) {
//
//    }
}
