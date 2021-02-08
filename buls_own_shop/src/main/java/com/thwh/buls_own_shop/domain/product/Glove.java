package com.thwh.buls_own_shop.domain.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("G")
@Getter
@Setter
public class Glove extends Product{
    private String position;
    private int size_glove;
}
