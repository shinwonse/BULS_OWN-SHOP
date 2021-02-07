package com.thwh.buls_own_shop.service;

import com.thwh.buls_own_shop.domain.product.Product;
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

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public Product findOne(Long productId) {
        return productRepository.findOne(productId);
    }

    public Product updateProduct(Long productId, String name, String brand, int price, int stockQuantity) {
        Product product = productRepository.findOne(productId);
        product.setId(productId);
        product.setName(name);
        product.setBrand(brand);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        return product;
    }
}
