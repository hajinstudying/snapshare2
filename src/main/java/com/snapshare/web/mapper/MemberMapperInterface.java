package com.snapshare.web.mapper;

import com.snapshare.web.vo.MemberVo;

/**
 * 멤버 매퍼 인터페이스
 */
//@Mapper
public interface MemberMapperInterface {
    int createMember(MemberVo memberVo);   // 회원가입
    MemberVo getMember(String memberId);   // 회원 한명 조회
}
