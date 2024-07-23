package com.snapshare.web.service;

import java.util.List;

import com.snapshare.web.vo.ReplyVo;

public interface ReplyService {
	public ReplyVo getReply(int replyId);
	public List<ReplyVo> listReply();                   
	public int createReply(ReplyVo replyVo);           
	public int updateReply(ReplyVo replyVo);
	public int deleteReply(int replyId);
	public int createReplySelectKey(ReplyVo replyVo);  
}
