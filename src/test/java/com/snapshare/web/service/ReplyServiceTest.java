package com.snapshare.web.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.snapshare.web.vo.ReplyVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 단위테스트
 * - SpringJUnit4ClassRunner 의존성이 안들어오는 경우 pom.xml에 spring-test 확인할것.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Slf4j
public class ReplyServiceTest {
	 
	// ReplyService 인터페이스 의존성 주입
	// 인터페이스 타입으로 의존성 주입했지만 실제로는 그 자식인 ReplyServiceImpl 주입됨.
	@Autowired
	private ReplyService replyService;
	
	@Test //@Ignore
	public void testReplyService() {
		assertNotNull(replyService);
		log.info("replyService 인터페이스 : " + replyService); // ReplyServiceImpl@7063686f
	}
	
	@Test @Ignore
	public void testGetReply() {
		int replyId = 11;
		ReplyVo replyVo = replyService.getReply(replyId);
		assertNotNull(replyVo);
		log.info("서비스에서 조회한 getReply() : " + replyVo);
	}
	
	@Test //@Ignore
	public void testListReply() {
		assertTrue(replyService.listReply().size() > 0);
		List<ReplyVo> replyList = replyService.listReply();
		replyList.forEach(reply -> log.info(reply.toString()));		
	}
	 
	@Test //@Ignore
	public void testCreateReplySelectKey() {
		// 저장할 객체 생성
		ReplyVo replyVo = new ReplyVo();
		replyVo.setMemberId("test");
		replyVo.setReplyContent("새로운 댓글 내용"); 
		replyVo.setBoardId(12);
		replyVo.setReplyGroup(0);
		replyVo.setReplyOrder(0);
		replyVo.setReplyIndent(0);
		
		// 객체 저장
		int result = replyService.createReplySelectKey(replyVo);
		assertTrue(result > 0);
		log.info("저장된 행수 : " + result);
	}
	
	@Test @Ignore
	public void testUpdateReply() {
		 // 수정할 객체 생성
		 ReplyVo replyVo = new ReplyVo();
		 replyVo.setReplyId(12);	// 실제로 DB에 있는 replyId
		 replyVo.setMemberId("test");
		 replyVo.setBoardId(1);
		 replyVo.setReplyGroup(0);
		 replyVo.setReplyOrder(1);
		 replyVo.setReplyIndent(1);
		 replyVo.setReplyContent("수정된 댓글 내용"); // replyContent 설정
		 
		 // 객체 수정
		 int result = replyService.updateReply(replyVo);
		 assertTrue(result > 0);
		 log.info("수정된 행수 : " + result);		
	}
	 
	@Test //@Ignore
	public void testDeleteReply() {
		int replyId = 12;	// 삭제할 replyId, DB에 있는 번호
		 
		// 객체 삭제
		int result = replyService.deleteReply(replyId);
		assertTrue(result > 0);
		log.info("삭제된 행수 : " + result);
	}	
	

}
