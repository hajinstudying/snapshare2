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

import com.snapshare.web.vo.BoardVo;

import lombok.extern.slf4j.Slf4j;

/**
 * [보드 매퍼 테스트]
 */

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"		
})
public class BoardMapperTest {
	
	@Autowired
	private DataSource dataSource; // import javax.sql.DataSource
	@Autowired
	private BoardMapperInterface boardMapper;
	
	@Test @Ignore
	public void testDataSource() {
		try (Connection conn = dataSource.getConnection()){
			assertNotNull(conn);
			System.out.println("획득한 커넥션 : " + conn);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test @Ignore
	public void testBoardMapper() {
		assertNotNull(boardMapper);
		log.info("boardMapper 객체 : " + boardMapper); //매퍼 프록시가 들어옴 org.apache.ibatis.binding.MapperProxy@6a0cbc6f
	}
	
	/**
	 * 게시물 상세조회 테스트 메소드
	 */
	@Test @Ignore
	public void testGetBoard() {
		int boardId = 5;
		BoardVo boardVo = boardMapper.getBoard(boardId);
		log.info("게시물 : " + boardVo);
	}
	
	/**
	 * 게시물 목록보기 테스트 메소드
	 */
	@Test @Ignore
	public void testListBoard() {
		List<BoardVo> boardList = boardMapper.listBoard();
		assertNotNull(boardList);
		assertTrue(boardList.size() > 0);
		boardList.forEach(board -> System.out.println(board));
	}
	
	/**
	 * 게시물 등록 테스트 메소드
	 */
	@Test @Ignore
	public void testCreateBoard() {
		//저장 객체 생성
		BoardVo boardVo = new BoardVo();
		boardVo.setFileName("imageName");
		boardVo.setMemberId("test");
		
		// 객체 저장
		int result = boardMapper.createBoardSelectKey(boardVo);
		assertTrue(result > 0);
		log.info("저장된 행수 : " + result);
		
	}
	
	/**
	 *  게시물 삭제 테스트 메소드
	 */
	@Test @Ignore
	public void testDeleteBoard() {
		int boardId = 4;
		
		// 객체 삭제
		int result = boardMapper.deleteBoard(boardId);
		assertTrue(result > 0);
		log.info("삭제된 행수 : " + result); 
	}
}