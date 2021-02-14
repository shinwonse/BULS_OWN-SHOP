package com.thwh.buls_own_shop.service;


import com.thwh.buls_own_shop.domain.Member;
import com.thwh.buls_own_shop.domain.Order;
import com.thwh.buls_own_shop.domain.OrderItem;
import com.thwh.buls_own_shop.domain.product.Product;
import com.thwh.buls_own_shop.repository.MemberRepository;
import com.thwh.buls_own_shop.repository.OrderRepository;
import com.thwh.buls_own_shop.repository.OrderSearch;
import com.thwh.buls_own_shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Product product = productRepository.findOne(itemId);

        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(product, product.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);

        // 주문 취소
        order.cancel();
    }

    // 검색
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }
}
