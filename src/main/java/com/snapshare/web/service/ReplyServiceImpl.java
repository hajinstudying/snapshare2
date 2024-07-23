package com.snapshare.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snapshare.web.mapper.ReplyMapperInterface;
import com.snapshare.web.vo.ReplyVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapperInterface replyMapper;

    @Override
    public ReplyVo getReply(int replyId) {
        ReplyVo replyVo = replyMapper.getReply(replyId);
        return replyVo;
    }

    @Override
    public List<ReplyVo> listReply() {
        return replyMapper.listReply();
    }

    @Override
    @Transactional
    public int createReply(ReplyVo replyVo) {
        return replyMapper.createReply(replyVo);
    }

    @Override
    public int updateReply(ReplyVo replyVo) {
        return replyMapper.updateReply(replyVo);
    }

    @Override
    public int deleteReply(int replyId) {
        return replyMapper.deleteReply(replyId);
    }

    @Override
    public int createReplySelectKey(ReplyVo replyVo) {
        return replyMapper.createReplySelectKey(replyVo);
    }
}
