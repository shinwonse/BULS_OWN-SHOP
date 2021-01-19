package com.thwh.buls_own_shop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "original_id")
    private Long original_id;

    private String user_id;
    private String birth;
    private String name;
    private String phoneNumber;

    @Embedded
    private Address address;

//    @OneToMany(mappedBy = "member")
//    private List<Order> orders = new ArrayList<>();
}
