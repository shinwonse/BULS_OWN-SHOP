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
        spike.setImageLink(productForm.getImageLink());

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
        bat.setImageLink(productForm.getImageLink());

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
        glove.setImageLink(productForm.getImageLink());

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

    public void updateProduct(Long productId, ProductForm productForm) {
        Product product = productRepository.findOne(productId);
        product.setId(productId);
        product.setCategory(productForm.getProductType());
        product.setName(productForm.getName());
        product.setBrand(productForm.getBrandName());
        product.setImageLink(productForm.getImageLink());
        product.setPrice(productForm.getPrice());
        product.setStockQuantity(productForm.getPrice());
        
        switch (productForm.getProductType()) {
            case "Glove":
                updateGlove((Glove) product, productForm);
                productRepository.save(product);
                return;
            case "Bat":
                updateBat((Bat) product, productForm);
                productRepository.save(product);
                return;
            case "Spike":
                updateSpike((Spike) product, productForm);
                productRepository.save(product);
                return;
        }

        // switch 문 내에서 함수가 끝나야 함
        throw new IllegalStateException("잘못된 접근입니다.");
    }


    public void updateGlove(Glove glove, ProductForm productForm) {
        glove.setSize_glove(productForm.getGloveSize());
        glove.setPosition(productForm.getPosition());
    }

    public void updateBat(Bat bat, ProductForm productForm) {
        bat.setSize_bat(productForm.getBatSize());
        bat.setMaterial(productForm.getMaterial());
    }

    public void updateSpike(Spike spike, ProductForm productForm) {
        spike.setSize_spike(productForm.getSpikeSize());
        spike.setSole(productForm.getSole());
    }


    public void makeDefaultGlove() {
        Glove glove = new Glove();
        glove.setName("제트 BPROG760 (1900) 프로 스테이터스 세컨드 11.5인치 내야 글러브 (블랙)");
        glove.setBrand("Zett");
        glove.setImageLink("/img/glove_images/glove_ex.jpg");
        glove.setPrice(1000);
        glove.setStockQuantity(10);
        glove.setCategory("Glove");
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
        bat.setCategory("Bat");

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
        spike.setCategory("Spike");

        spike.setSole("징");
        spike.setSize_spike(270);

        List<Product> productList = productRepository.findByName(spike.getName());
        if(!productList.isEmpty()){
            return;
        }
        productRepository.save(spike);
    }

    public ProductForm setOldProductForm(Product product) {
        ProductForm productForm = new ProductForm();
        productForm.setId(product.getId());
        productForm.setName(product.getName());
        productForm.setPrice(product.getPrice());
        productForm.setProductType(product.getCategory());
        productForm.setBrandName(product.getBrand());
        productForm.setStockQuantity(product.getStockQuantity());
        productForm.setImageLink(product.getImageLink());

        switch(product.getCategory()){
            case "Glove":
                setOldGloveForm(productForm, (Glove)product);
                return productForm;
            case "Bat":
                setOldBatForm(productForm, (Bat)product);
                return productForm;
            case "Spike":
                setOldSpikeForm(productForm, (Spike)product);
                return productForm;
        }
        return productForm;
    }

    private void setOldGloveForm(ProductForm productForm, Glove glove) {
        productForm.setGloveSize(glove.getSize_glove());
        productForm.setPosition(glove.getPosition());
    }

    private void setOldBatForm(ProductForm productForm, Bat bat) {
        productForm.setBatSize(bat.getSize_bat());
        productForm.setMaterial(bat.getMaterial());
    }

    private void setOldSpikeForm(ProductForm productForm, Spike spike) {
        productForm.setSpikeSize(spike.getSize_spike());
        productForm.setSole(spike.getSole());
    }
}
