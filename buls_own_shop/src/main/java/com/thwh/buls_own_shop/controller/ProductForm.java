package com.thwh.buls_own_shop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class ProductForm {
    @NotEmpty(message = "상품 명은 필수입니다.")
    private String name;

    private int price;

    private int stockQuantity;

    private String productType; // Glove, Bat, Spike

    @NotEmpty(message = "브랜드 명은 필수입니다.")
    private String brandName;

    private String imageLink;

    // Glove
    private String position;
    private double gloveSize;

    // Bat
    private String material;
    private String batSize;

    // Spike
    private String sole;
    private int spikeSize;


}
