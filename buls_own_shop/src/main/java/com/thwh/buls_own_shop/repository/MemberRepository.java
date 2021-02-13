package com.thwh.buls_own_shop.repository;

import com.thwh.buls_own_shop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository // Repository 라는 뜻
@RequiredArgsConstructor // EntityManager 를 선언하고 따로 생성자, 게터, 세터를 안 만들어줘도 됨
public class MemberRepository {
    
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long original_id) {
        return em.find(Member.class, original_id); // find 라는 내장함수로 Member class 내에서 original_id에 해당하는 Member 객체를 리턴해줌
    }

    // 이름을 통해 멤버 객체 리스트를 리턴해줌 (JPA 문법을 정확히 익히지 못해 이해하지 못한 구문임)
    public List<Member> findById(String id) {
        return em.createQuery("select m from Member m where m.user_id = :id", Member.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
