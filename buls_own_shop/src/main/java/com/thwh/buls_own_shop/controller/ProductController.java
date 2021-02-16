package com.thwh.buls_own_shop.controller;

import com.thwh.buls_own_shop.domain.product.Bat;
import com.thwh.buls_own_shop.domain.product.Glove;
import com.thwh.buls_own_shop.domain.product.Product;
import com.thwh.buls_own_shop.domain.product.Spike;
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
        productService.makeDefaultGlove();
        List<Glove> gloves = productService.findGloves();
        model.addAttribute("gloves", gloves);
        return "/product/glove/glove_list";
    }

    @GetMapping("/product/glove/glove_buy/{id}")
    public String glove_buy(@PathVariable("id") Long id, Model model) {
        Glove glove = (Glove) productService.findOne(id);

        model.addAttribute("glove",glove);
        return "/product/glove/glove_buy";
    }

    @GetMapping("/product/bat")
    public String bat_list(Model model) {
        productService.makeDefaultBat();
        List<Bat> bats = productService.findBats();
        model.addAttribute("items", bats);
        return "/product/bat/bat_list";
    }

    @GetMapping("/product/bat/bat_buy/{id}")
    public String bat_buy(@PathVariable("id") Long id, Model model) {
        Bat bat = (Bat) productService.findOne(id);

        model.addAttribute("bat",bat);
        return "/product/bat/bat_buy";
    }

    @GetMapping("/product/spike")
    public String spike_list(Model model) {
        productService.makeDefaultSpike();
        List<Spike> spikes = productService.findSpikes();
        model.addAttribute("items", spikes);
        return "/product/spike/spike_list";
    }

    @GetMapping("/product/spike/spike_buy/{id}")
    public String spike_buy(@PathVariable("id") Long id, Model model) {
        Spike spike = (Spike) productService.findOne(id);

        model.addAttribute("spike",spike);
        return "/product/spike/spike_buy";
    }
}
