package com.thwh.buls_own_shop.controller;

import com.thwh.buls_own_shop.domain.Member;
import com.thwh.buls_own_shop.domain.Order;
import com.thwh.buls_own_shop.domain.product.Product;
import com.thwh.buls_own_shop.repository.OrderSearch;
import com.thwh.buls_own_shop.service.MemberService;
import com.thwh.buls_own_shop.service.OrderService;
import com.thwh.buls_own_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ProductService productService;

    @GetMapping("/order/{productId}")
    public String createForm(@PathVariable("productId") Long productId, HttpSession session, Model model) {
        if(session.getAttribute("member") == null) // 로그인 안 했을 시 로그인 페이지로 넘어가게 함
            return "redirect:/members/login";

        Product product = productService.findOne(productId);
        Member member = (Member) session.getAttribute("member");
        model.addAttribute("member", member);
        model.addAttribute("product", product);
        return "/order/orderForm";
    }

    @PostMapping("/order/{productId}")
    public String order(HttpSession session,
                        @RequestParam("count") int count,
                        @PathVariable("productId") Long productId) {
        Member member = (Member) session.getAttribute("member");
        Product product = productService.findOne(productId);

        orderService.order(member.getOriginal_id(), product.getId(), count);
        return "redirect:/members/" + member.getUser_id() + "/myPage";
    }

    @GetMapping("/order/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId, HttpSession session) {
        orderService.cancelOrder(orderId);
        Member member = (Member) session.getAttribute("member");

        return "redirect:/members/" + member.getUser_id() + "/myPage";
    }
}
