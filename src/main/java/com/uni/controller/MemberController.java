package com.uni.controller;

import com.uni.entity.Member;
import com.uni.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public Member addMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody Member member) {
        return memberService.updateMember(id, member);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }

    @GetMapping("/username/{username}")
    public List<Member> getMemberByUsername(@PathVariable String username) {
        return memberService.getMemberByUsername(username);
    }

    @GetMapping("/username/{username}/password/{password}")
    public boolean validMemberByUsernameAndPassword(@PathVariable String username,@PathVariable String password) {
        return memberService.validMemberByUsernameAndPassword(username,password);
    }

}
