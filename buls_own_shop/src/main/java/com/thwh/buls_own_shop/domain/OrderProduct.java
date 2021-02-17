package com.thwh.buls_own_shop.domain;


import com.thwh.buls_own_shop.domain.product.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int OrderPrice; // 주문 당시 가격
    private int count; // 주문 수량

    //==생성 메서드==//
    public static OrderProduct createOrderProduct(Product product, int orderPrice, int count) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setOrderPrice(orderPrice);
        orderProduct.setCount(count);

        product.subtractStock(count);
        return orderProduct;


    }


    //==비즈니스 로직==//
    public void cancel() {
        getProduct().addStock(count); // 재고 수량 원상복구
    }

    //==조회 로직==//
    /**
     * 주문 상품 전체 가격 조회
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
