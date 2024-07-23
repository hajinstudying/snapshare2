package com.snapshare.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snapshare.web.mapper.MemberMapper;
import com.snapshare.web.vo.MemberVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 멤버 서비스 인터페이스 구현체
 * - 실제로 비즈니스 로직이 구현되는 클래스
 *
 */
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    @Transactional
    public int createMember(MemberVo memberVo) {
        validateMemberId(memberVo.getMemberId());
        int result = memberMapper.createMember(memberVo);
        return result;
    }

    @Override
    public MemberVo getMember(String memberId) {
        validateMemberId(memberId);
        MemberVo member = memberMapper.getMember(memberId);
        return member;
    }

    private void validateMemberId(String memberId) {
        if (memberId == null || memberId.isEmpty()) {
            log.error("Member ID cannot be null or empty");
            throw new IllegalArgumentException("Member ID cannot be null or empty");
        }
    }
}
