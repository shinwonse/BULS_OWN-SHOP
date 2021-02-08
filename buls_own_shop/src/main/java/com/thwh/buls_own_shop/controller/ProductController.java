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
        glove.setName("제트 BPROG760 (1900) 프로 스테이터스 세컨드 11.5인치 내야 글러브 (블랙)");
        glove.setBrand("Zett");
        glove.setImageLink("/img/glove_images/glove_ex.jpg");
        glove.setPosition("내야");
        glove.setSize_glove(11.5);
        glove.setPrice(1000);
        glove.setStockQuantity(10);
        productService.saveProduct(glove);
        List<Product> gloves = productService.findProducts();
        model.addAttribute("items", gloves);
        return "/product/glove/glove_list";
    }

    @GetMapping("/product/glove/glove_buy/{id}")
    public String glove_buy(@PathVariable("id") Long id, Model model) {
        Glove glove = (Glove) productService.findOne(id);
        glove.setId(glove.getId());
        glove.setName(glove.getName());
        glove.setBrand(glove.getBrand());
        glove.setImageLink(glove.getImageLink());
        glove.setSize_glove(glove.getSize_glove());
        glove.setPrice(glove.getPrice());
        model.addAttribute("glove",glove);
        return "/product/glove/glove_buy";
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
}
