package com.thwh.buls_own_shop.domain.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
public class Bat extends Product{
    private String size_bat;    // 33-28
    private String material;    // 나무, 풀 알로이
}
