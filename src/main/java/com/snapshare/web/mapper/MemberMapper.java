package com.snapshare.web.mapper;

import com.snapshare.web.vo.MemberVo;

/**
 * 멤버 매퍼 인터페이스
 * - MemberService와 MemberMapper.xml을 연결시켜주는 역할.
 */
//@Mapper
public interface MemberMapper {
    int createMember(MemberVo memberVo);	// 회원가입
    MemberVo getMember(String memberId);	// 회원 한명 조회
}
