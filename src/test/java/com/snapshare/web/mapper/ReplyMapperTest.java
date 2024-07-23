package com.snapshare.web.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

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
public class ReplyMapperTest {
	 
	@Autowired
	private DataSource dataSource;
	@Autowired
	private ReplyMapperInterface replyMapper;
	
	@Test @Ignore
	public void testDataSource() {
		try(Connection conn = dataSource.getConnection()){
			assertNotNull(conn);
			System.out.println("획득한 커넥션 : " + conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 매퍼 인터페이스의 의존성 주입이 정상적으로 되는지 확인하는 테스트 메소드
	 */
	@Test @Ignore
	public void testReplyMapper() {
		assertNotNull(replyMapper);		
		log.info("replyMapper 객체 : " + replyMapper);
	}

	// 댓글 상세보기 테스트
	@Test @Ignore
	public void testGetReply() {
		int replyId = 1; // 실제 댓글 ID
		ReplyVo replyVo = replyMapper.getReply(replyId);
		log.info("댓글 : " + replyVo);
	}
	 
	// 댓글 목록보기 테스트
	@Test @Ignore
	public void testListReply() {
		List<ReplyVo> replyList = replyMapper.listReply();
		assertNotNull(replyList);
		assertTrue(replyList.size() > 0);
		replyList.forEach(reply -> System.out.println(reply));
	}
	 
	// 댓글 등록 테스트
	@Test @Ignore
	public void testCreateReplySelectKey() {
	    // 저장할 객체 생성
	    ReplyVo replyVo = new ReplyVo();
	    replyVo.setMemberId("test");
	    replyVo.setBoardId(4);
	    replyVo.setReplyGroup(0);
	    replyVo.setReplyOrder(0);
	    replyVo.setReplyIndent(0);
	    replyVo.setReplyContent("새로운 댓글 내용"); 
	     
	    // 객체 저장
	    int result = replyMapper.createReplySelectKey(replyVo);
	    assertTrue(result > 0);
		log.info("저장된 행수 : " + result);
	}
	 
	// 댓글 수정 테스트
	@Test @Ignore
	public void testUpdateReply() {
		// 수정할 객체 생성
		ReplyVo replyVo = new ReplyVo();
		replyVo.setReplyId(2);	
		replyVo.setMemberId("test");
		replyVo.setBoardId(2);
		replyVo.setReplyGroup(0);
		replyVo.setReplyOrder(1);
		replyVo.setReplyIndent(1);
		replyVo.setReplyContent("수정된 댓글 내용"); // replyContent 설정
		 
		// 객체 수정
		int result = replyMapper.updateReply(replyVo);
		assertTrue(result > 0);
		log.info("수정된 행수 : " + result);
	}
	 
	// 댓글 삭제 테스트
	@Test //@Ignore
	public void testDeleteReply() {
		int replyId = 2;	// 삭제할 replyId, DB에 있는 번호
		 
		// 객체 삭제
		int result = replyMapper.deleteReply(replyId);
		assertTrue(result > 0);
		log.info("삭제된 행수 : " + result);
	}
}
