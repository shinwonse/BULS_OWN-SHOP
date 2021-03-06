package com.thwh.buls_own_shop.service;

import com.thwh.buls_own_shop.controller.LoginForm;
import com.thwh.buls_own_shop.controller.MemberForm;
import com.thwh.buls_own_shop.domain.Address;
import com.thwh.buls_own_shop.domain.Member;
import com.thwh.buls_own_shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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
        Member member = new Member(memberForm.getName(), memberForm.getUser_id(), memberForm.getPw(), memberForm.getBirth(),
                                        memberForm.getPhoneNumber(), address);

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

    /**
     * 로그인
     */
    public String signIn(LoginForm loginForm) {
        List<Member> ids = memberRepository.findById(loginForm.getId());
        if(ids == null){
            throw new IllegalStateException("존재하지 않는 ID입니다.");
        }
        else{
            Member member = ids.get(0);
            if(member.getPw().equals(loginForm.getPw()))
                return member.getUser_id();
            else
                throw new IllegalStateException("잘못된 PW입니다.");
        }
    }

    /**
     * 회원조회
     */
    // 회원 전체 조회
    // @Transactional(readOnly = true)
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 한 명 조회
    // @Transactional(readOnly = true)
    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }

    public Member findByStringId(String strId) {
        List<Member> id_list = memberRepository.findById(strId);
        return id_list.get(0);
    }

    // root 아이디 자동 생성 메서드
    public void rootDefault() {
        Member member = new Member("Administrator", "root", "1234", "none", "none", null);
        List<Member> members = memberRepository.findById(member.getUser_id());
        if (!members.isEmpty()) {
            return;
        }
        memberRepository.save(member);
    }

}
