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



}
