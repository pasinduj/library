package com.uni.service;

import com.uni.entity.Member;
import com.uni.repo.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member updateMember(Long id, Member updated) {
        return memberRepository.findById(id)
                .map(m -> {
                    m.setFirstname(updated.getFirstname());
                    m.setEmail(updated.getEmail());
                    return memberRepository.save(m);
                }).orElseThrow(() -> new RuntimeException("Member not found"));
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    public List<Member> getMemberByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public boolean validMemberByUsernameAndPassword(String username, String password) {
        return memberRepository.existsByUsernameAndPassword(username, password);
    }

}
