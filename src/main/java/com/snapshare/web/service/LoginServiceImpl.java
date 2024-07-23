package com.snapshare.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snapshare.web.mapper.LoginMapperInterface;
import com.snapshare.web.vo.MemberVo;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapperInterface loginMapper;

    @Override
    public MemberVo login(MemberVo memberVo) {
        return loginMapper.login(memberVo);
    }
}
