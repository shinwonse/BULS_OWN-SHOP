package com.thwh.buls_own_shop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @GeneratedValue
    @Column(name = "original_id")
    private Long original_id;

    @Id
    private String user_id;

    private String pw;
    private String birth;
    private String name;
    private String phoneNumber;

    @Embedded
    private Address address;

    protected Member() { }

    public Member(String name, String user_id, String pw, String birth,  String phoneNumber, Address address) {
        this.name = name;
        this.user_id = user_id;
        this.pw = pw;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    //    @OneToMany(mappedBy = "member")
//    private List<Order> orders = new ArrayList<>();
}
