package com.snapshare.web.service;

import com.snapshare.web.vo.MemberVo;

/**
 * Member 서비스 인터페이스
 * - 비즈니스 로직이 구현되는 레이어
 *
 */
public interface MemberService {
    int createMember(MemberVo member);
    MemberVo getMember(String memberId);
}