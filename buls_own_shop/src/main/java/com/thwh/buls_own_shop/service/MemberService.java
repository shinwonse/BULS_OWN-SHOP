package com.thwh.buls_own_shop.service;

import com.thwh.buls_own_shop.controller.MemberForm;
import com.thwh.buls_own_shop.domain.Address;
import com.thwh.buls_own_shop.domain.Member;
import com.thwh.buls_own_shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long signUp(MemberForm memberForm) {
        Address address = new Address(memberForm.getCity(), memberForm.getStreet());
        Member member = new Member();

        member.setUser_id(memberForm.getUser_id());
        member.setName(memberForm.getName());
        member.setBirth(memberForm.getBirth());
        member.setPhoneNumber(memberForm.getPhoneNumber());
        member.setAddress(address);

        validateDuplicateMember(member); // 아이디 중복되는 회원 체크
        memberRepository.save(member);   // 레포지토리에 저장(DB로 슝)
        return member.getOriginal_id();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> members = memberRepository.findById(member.getUser_id());
        if (!members.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 ID입니다.");
        }
    }

    // 회원 전체 조회
    // @Transactional(readOnly = true)
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 한 명 조회
    // @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
