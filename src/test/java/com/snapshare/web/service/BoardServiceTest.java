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

import com.snapshare.web.vo.BoardVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 게시물 서비스 테스트
 */

@Slf4j 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"		
})
public class BoardServiceTest {
	
	@Autowired
	private BoardService boardService;
	
	/**
	 * BoardService 의존성 주입 확인 테스트
	 */
	@Test @Ignore
	public void testBoardService() {
		assertNotNull(boardService);
		log.info("boardService 인터페이스 : " + boardService);
	}
	
	/**
	 * 게시물 상세 조회 테스트 메소드
	 */
	@Test @Ignore
	public void testGetBoard() {
		int boardId = 7;
		BoardVo boardVo = boardService.getBoard(boardId);
		assertNotNull(boardVo);
		log.info("서비스에서 조회한 getBoard : " + boardVo);
	}
	
	/**
	 * 게시물 목록 조회 테스트 메소드
	 */
	@Test @Ignore
	public void testListBoard() {
		// 0보다 크면 목록이 있다는 의미
		assertTrue(boardService.listBoard().size() > 0);
		List<BoardVo> boardList = boardService.listBoard();
		boardList.forEach(board -> log.info(board.toString())); // log.info()안에는 문자열만 와야함
	}

	/**
	 * 게시물 등록 테스트 메소드
	 * - createBoardSelectKey()가 아닌 createBoard()사용
	 */
	/*
	 * @Test @Ignore public void testCreateBoard() { //저장 객체 생성 BoardVo boardVo =
	 * new BoardVo(); boardVo.setFileName("imageName"); boardVo.setMemberId("test");
	 * 
	 * // 객체 저장 int result = boardService.createBoard(boardVo); assertTrue(result >
	 * 0); log.info("저장된 행수 : " + result); }
	 */
	
	/**
	 *  게시물 삭제 테스트 메소드
	 */
	@Test //@Ignore
	public void testDeleteBoard() {
		int boardId = 27;
		
		// 객체 삭제
		int result = boardService.deleteBoard(boardId);
		assertTrue(result > 0);
		log.info("삭제된 행수 : " + result); 
	}
	
}
