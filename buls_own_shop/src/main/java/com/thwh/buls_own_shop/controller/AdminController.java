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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;
    private final MemberService memberService;

    private final String UPLOAD_DIR = "/static/img/";

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
    public String create(@Valid ProductForm productForm,
                         BindingResult result,
                         MultipartHttpServletRequest multipartHttpServletRequest) {
        if (result.hasErrors()) {
            return "administrator/createProductForm";
        }
        if(productForm.getImageLink() != null) {
            // String src = multipartHttpServletRequest.getParameter("src");
            MultipartFile multipartFile = multipartHttpServletRequest.getFile("file");
            String originFileName = multipartFile.getOriginalFilename(); // 원본 파일 명
            long fileSize = multipartFile.getSize(); // 파일 사이즈

            // 반드시 자신의 로컬 저장소로 바꿀 것
            String PATH = "C:/Users/Jeong-EuiJae/Documents/GitHub/BULS_OWN-SHOP/buls_own_shop/src/main/resources/static/img/glove_images/";

            Long currentTime = System.currentTimeMillis();
            String filePath = PATH + currentTime + originFileName;
            try {
                multipartFile.transferTo(new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            productForm.setImageLink("/img/glove_images/" + currentTime.toString() + originFileName);
        }
        else{
            productForm.setImageLink("/img/preparing_image.jpg");
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

    @GetMapping("admin/{productId}/edit")
    public String updateProductForm(@PathVariable("productId") Long productId, Model model) {
        Product product = productService.findOne(productId);
        ProductForm oldProductForm = productService.setOldProductForm(product);

        model.addAttribute("productForm", oldProductForm);
        return "administrator/updateProductForm";
    }

    @PostMapping("admin/{productId}/edit")
    public String update(@PathVariable("productId") Long productId,
                         @Valid ProductForm productForm,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "administrator/createProductForm";
        }
        productService.updateProduct(productId, productForm);

        return "redirect:/admin/productList";
    }
}
