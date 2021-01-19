package com.thwh.buls_own_shop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;

    protected Address(){
        // 값 타입은 변경 불가능하게 설계해야함. 따라서 Setter를 안 줌
    }

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }
}
