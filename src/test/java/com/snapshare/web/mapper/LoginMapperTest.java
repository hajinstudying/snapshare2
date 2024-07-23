package com.snapshare.web.mapper;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.snapshare.web.mapper.LoginMapperInterface;
import com.snapshare.web.vo.MemberVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 단위테스트
 * - SpringJUnit4ClassRunner 의존성이 안들어오는 경우 pom.xml에 spring-test 확인할것.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Slf4j
public class LoginMapperTest {
    
    // 매퍼 인터페이스 의존성 주입
    @Autowired
    private LoginMapperInterface loginMapper;
    
    /**
     * 매퍼 인터페이스의 의존성 주입이 정상적으로 되는지 확인하는 테스트 메소드
     */
    @Test
    @Ignore
    public void testBoardMapper() {
        assertNotNull(loginMapper);        
        log.info("loginMapper 객체 : " + loginMapper);
    }

    // 게시물 상세보기 테스트
    @Test
    @Ignore
    public void testLogin() {
        MemberVo memberVo = new MemberVo();
        memberVo.setMemberId("java");
        memberVo.setPassword("1234");
        MemberVo loginMemberVo = loginMapper.login(memberVo);
        assertNotNull(loginMemberVo);
        log.info("로그인 사용자 : " + loginMemberVo);
    } 

}
