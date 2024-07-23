package com.snapshare.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snapshare.web.service.MemberService;
import com.snapshare.web.vo.MemberVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/create")
    public String createMemberForm(Model model) {
        model.addAttribute("memberVo", new MemberVo());
        return "member/memberCreate";
    }
    @PostMapping("/create")
    public String createMember(@ModelAttribute MemberVo memberVo) {
        log.info("createMember method: {}", memberVo);
        memberVo.setMemberId(memberVo.getName());
        memberVo.setRole("User");
        memberService.createMember(memberVo);
        return "redirect:/member/list";
    }


    @GetMapping("/{memberId}")
    public String getMember(@PathVariable("memberId") String memberId, Model model) {
        MemberVo memberVo = memberService.getMember(memberId);
        if (memberVo == null) {
            log.error("Member not found with ID: {}", memberId);
            return "login/login";
        }
        model.addAttribute("memberVo", memberVo);
        return "member/memberDetail";
    }
}
