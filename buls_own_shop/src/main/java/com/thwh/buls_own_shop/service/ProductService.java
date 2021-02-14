package com.thwh.buls_own_shop.service;

import com.thwh.buls_own_shop.controller.ProductForm;
import com.thwh.buls_own_shop.domain.product.Bat;
import com.thwh.buls_own_shop.domain.product.Glove;
import com.thwh.buls_own_shop.domain.product.Product;
import com.thwh.buls_own_shop.domain.product.Spike;
import com.thwh.buls_own_shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void saveProduct(ProductForm productForm) {
        String productType = productForm.getProductType();
        switch (productType) {
            case "Glove":
                saveGlove(productForm);
                return;
            case "Bat":
                saveBat(productForm);
                return;
            case "Spike":
                saveSpike(productForm);
                return;
        }
    }

    private void saveSpike(ProductForm productForm) {
        Spike spike = new Spike();
        spike.setName(productForm.getName());
        spike.setPrice(productForm.getPrice());
        spike.setStockQuantity(productForm.getStockQuantity());
        spike.setBrand(productForm.getBrandName());
        spike.setCategory(productForm.getProductType());

        spike.setSize_spike(productForm.getSpikeSize());
        spike.setSole(productForm.getSole());
        productRepository.save(spike);
    }

    private void saveBat(ProductForm productForm) {
        Bat bat = new Bat();
        bat.setName(productForm.getName());
        bat.setPrice(productForm.getPrice());
        bat.setStockQuantity(productForm.getStockQuantity());
        bat.setBrand(productForm.getBrandName());
        bat.setCategory(productForm.getProductType());

        bat.setSize_bat(productForm.getBatSize());
        bat.setMaterial(productForm.getMaterial());
        productRepository.save(bat);
    }

    private void saveGlove(ProductForm productForm) {
        Glove glove = new Glove();
        glove.setName(productForm.getName());
        glove.setPrice(productForm.getPrice());
        glove.setStockQuantity(productForm.getStockQuantity());
        glove.setBrand(productForm.getBrandName());
        glove.setCategory(productForm.getProductType());

        glove.setSize_glove(productForm.getGloveSize());
        glove.setPosition(productForm.getPosition());
        productRepository.save(glove);
    }

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public List<Glove> findGloves() {
        return productRepository.findGloves();
    }

    public List<Bat> findBats() {
        return productRepository.findBats();
    }

    public List<Spike> findSpikes() {
        return productRepository.findSpikes();
    }

    public Product findOne(Long productId) {
        return productRepository.findOne(productId);
    }

    public Product updateProduct(Long productId, String name, String brand, String imageLink, int price, int stockQuantity) {
        Product product = productRepository.findOne(productId);
        product.setId(productId);
        product.setName(name);
        product.setBrand(brand);
        product.setImageLink(imageLink);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        return product;
    }


    public void makeDefaultGlove() {
        Glove glove = new Glove();
        glove.setName("제트 BPROG760 (1900) 프로 스테이터스 세컨드 11.5인치 내야 글러브 (블랙)");
        glove.setBrand("Zett");
        glove.setImageLink("/img/glove_images/glove_ex.jpg");
        glove.setPrice(1000);
        glove.setStockQuantity(10);
        glove.setPosition("내야11");
        glove.setSize_glove(11.5);

        List<Product> productList = productRepository.findByName(glove.getName());
        if(!productList.isEmpty()){
            return;
        }
        productRepository.save(glove);
    }

    public void makeDefaultBat() {
        Bat bat = new Bat();
        bat.setName("드마리니 투피스 배트");
        bat.setBrand("드마리니");
        bat.setImageLink("/img/bat_images/bat_ex.jpg");
        bat.setPrice(10000);
        bat.setStockQuantity(10);
        bat.setMaterial("카본");
        bat.setSize_bat("33-28");
        List<Product> productList = productRepository.findByName(bat.getName());
        if(!productList.isEmpty()){
            return;
        }
        productRepository.save(bat);
    }

    public void makeDefaultSpike() {
        Spike spike = new Spike();
        spike.setName("뉴발란스 징 스파이크");
        spike.setBrand("뉴발란스");
        spike.setImageLink("/img/spike_images/spike_ex.jpg");
        spike.setPrice(100);
        spike.setStockQuantity(11);
        spike.setSole("징");
        spike.setSize_spike(270);

        List<Product> productList = productRepository.findByName(spike.getName());
        if(!productList.isEmpty()){
            return;
        }
        productRepository.save(spike);
    }

}
