package com.snapshare.web.service;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Slf4j
public class LoginServiceTest {

    @Autowired
    private LoginMapperInterface loginMapper;
    
    @Test
    @Ignore
    public void testLoginMapper() {
        assertNotNull(loginMapper);
    }
    
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
