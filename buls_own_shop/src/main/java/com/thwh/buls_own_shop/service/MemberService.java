package com.thwh.buls_own_shop.service;

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
    public Long signUp(Member member) {
        validateDuplicateMember(member); //
        memberRepository.save(member);
        return member.getOriginal_id();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> members = memberRepository.findById(member.getName());
        if (!members.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
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
