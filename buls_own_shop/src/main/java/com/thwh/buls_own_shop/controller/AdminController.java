package com.thwh.buls_own_shop.controller;

import com.thwh.buls_own_shop.domain.Member;
import com.thwh.buls_own_shop.domain.product.Product;
import com.thwh.buls_own_shop.service.MemberService;
import com.thwh.buls_own_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;
    private final MemberService memberService;

    @GetMapping("/admin")
    public String adminMain() {
        return "administrator/admin";
    }

    @GetMapping("/admin/newProduct")
    public String createProductForm(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "administrator/createProductForm";
    }

    @PostMapping("/admin/newProduct")
    public String create(@Valid ProductForm productForm, BindingResult result) {

        if (result.hasErrors()) {
            return "administrator/createProductForm";
        }

        productService.saveProduct(productForm);
        return "redirect:/admin";
    }

    @GetMapping("/admin/productList")
    public String productList(Model model) {
        List<Product> products = productService.findProducts();
        model.addAttribute("products", products);
        return "administrator/productList";
    }

    @GetMapping("/admin/memberList")
    public String memberList(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "administrator/memberList";
    }
}
