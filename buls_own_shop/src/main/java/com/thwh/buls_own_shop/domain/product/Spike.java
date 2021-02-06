package com.thwh.buls_own_shop.domain.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("S")
@Getter
@Setter
public class Spike extends Product{
    private int size_spike;
    private String sole;
}
