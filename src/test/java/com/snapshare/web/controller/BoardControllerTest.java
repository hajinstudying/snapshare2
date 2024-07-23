package com.snapshare.web.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.snapshare.web.service.BoardService;
import com.snapshare.web.vo.BoardVo;
import com.snapshare.web.vo.MemberVo;

/**
 * 컨트롤러 통합 테스트
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@WebAppConfiguration
public class BoardControllerTest {

	@Mock
	private BoardService service;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@InjectMocks
	private BoardController boardController;

	@Before // 초기화 작업
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
	}

	/**
	 * 게시물 내용보기 테스트
	 */
	@Test
	@Ignore
	public void testGetBoard() throws Exception {
		// given
		BoardVo boardVo = new BoardVo(1, "test", "imageName");
		Mockito.when(service.getBoard(1)).thenReturn(boardVo);

		// when
		this.mockMvc.perform(get("/board/detail").param("boardId", "1"))

				// then
				.andExpect(status().isOk()).andExpect(view().name("board/boardDetail"))
				.andExpect(model().attributeExists("boardVo")).andExpect(model().attribute("boardVo", boardVo));
	}

	/**
	 * 게시물 목록 테스트
	 */
	@Test
	@Ignore
	public void testListBoard() throws Exception {
		// given
		BoardVo boardVo1 = new BoardVo(1, "test", "imgName1");
		BoardVo boardVo2 = new BoardVo(2, "test", "imgName2");
		List<BoardVo> boardList = new ArrayList<>();
		boardList.add(boardVo1);
		boardList.add(boardVo2);

		Mockito.when(service.listBoard()).thenReturn(boardList);

		// when
		this.mockMvc.perform(get("/board/list"))

				// then
				.andExpect(status().isOk()).andExpect(view().name("board/boardList"))
				.andExpect(model().attributeExists("boardList")).andExpect(model().attribute("boardList", boardList));
	}

	/**
	 * 게시물 등록 테스트
	 * - 세션의 로그인여부를 확인하는 부분이 있음
	 */
	/*
	 * @Test @Ignore public void testCreateBoard() throws Exception { BoardVo
	 * boardVo = new BoardVo(1, "test", "imgName");
	 * 
	 * // given String memberId = "testUser";
	 * Mockito.when(service.createBoard(Mockito.any(BoardVo.class))).thenReturn(1);
	 * 
	 * // 가짜 세션 생성 HttpSession session = mock(HttpSession.class);
	 * when(session.getAttribute("memberVo")).thenReturn(new MemberVo(memberId));
	 * 
	 * // 가짜 이미지 파일 생성 InputStream inputStream =
	 * getClass().getResourceAsStream("/test-image.png"); MockMultipartFile file =
	 * new MockMultipartFile("file", "test-image.png", "image/png", inputStream);
	 * 
	 * // when this.mockMvc.perform(multipart("/board/create") .file(file)
	 * .sessionAttr("memberVo", session.getAttribute("memberVo")) .param("boardId",
	 * String.valueOf(boardVo.getBoardId())) .param("memberId",
	 * String.valueOf(boardVo.getMemberId())) .param("fileName",
	 * boardVo.getFileName())) // then .andExpect(status().is3xxRedirection())
	 * .andExpect(redirectedUrl("/board/list")); }
	 */
	
	/**
	 * 게시물 삭제 테스트 - 세션의 memberId와 boardId를 통해 가져온 BoardVo 객체의 membrId가 일치하는지 검증하는
	 * 부분을 설정한 뒤 삭제 메소드 테스트가 진행된다.
	 */
	@Test
	@Ignore
	public void testDeleteBoard() throws Exception {
		// given
		int boardId = 1;
		String memberId = "testUser";
		Mockito.when(service.deleteBoard(boardId)).thenReturn(1);

		// 가짜 세션 생성
		HttpSession session = mock(HttpSession.class);
		when(session.getAttribute("memberVo")).thenReturn(new MemberVo(memberId));

		// 게시물 조회 시 반환할 가짜 데이터 설정
		BoardVo boardVo = new BoardVo(boardId, memberId, "imgName");
		when(service.getBoard(boardId)).thenReturn(boardVo);

		// when
		mockMvc.perform(post("/board/delete").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("boardId", String.valueOf(boardId)).sessionAttr("memberVo", session.getAttribute("memberVo")))
				// then
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/board/list"));
	}
}
