package com.snapshare.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.snapshare.web.service.ReplyService;
import com.snapshare.web.vo.MemberVo;
import com.snapshare.web.vo.ReplyVo;

/**
 * 웹 어플리케이션 컨텍스트를 사용하겠다. 즉, 웹 어플리케이션의 설정파일을 사용하겠다.
 * 컨트롤러와 같은 웹 계층을 테스트할 때 사용한다.
 * 컨트롤러 통합 테스트의 필요성
 * - 실제 데이터베이스나 다른 외부 시스템에 접근하지 않고도 개발이 정상적으로 잘되었는지 검증해볼수있다.
 * - 서블릿 컨테이너를 구동하지 않고 마치 사용자가 실제로 요청을 한것과 같은 효과를 얻을 수 있다.
 *   서블릿 컨테이너는 구동하는 시간도 많이걸린다. 테스트 시간을 훨씬 단축해준다.
 * - 이 테스트를 통해서 개발자가 파라미터를 알맞은 타입을 정의했는지, 순서는 맞는지 등의
 *   다양한 테스트를 할수 있다.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@WebAppConfiguration // 웹 어플레케이션 설정 파일을 사용하겠다
public class ReplyControllerTest {

	// @Mock : 목 객체를 생성한다. 목은 가짜 객체로 실제 객체와 같은 동작을 한다.
	@Mock
	private ReplyService service;

	// WebApplicationContext : 웹 어플리케이션의 설정파일을 사용할 수 있게 해준다.
	// @WebAppConfiguration 어노테이션을 사용하면 사용할 수 있다.
	@Autowired
	private WebApplicationContext wac;

	// 가짜 mvc 객체로 역할은 컨트롤러 테스트를 위한 가짜 mvc 객체를 생성한다.
	// 이걸 생성하면 컨트롤러를 테스트할 수 있다.
	private MockMvc mockMvc;

	// @InjectMocks : 테스트 대상이 되는 객체에 목 객체를 주입한다. 서블릿 컨테이너를 실행하지 않아도 테스트 가능
	// ReplyController 객체에 목 객체를 주입한다. 이 구문을 생략하면 목 객체가 주입되지 않는다.
	@InjectMocks
	private ReplyController replyController;
	
	@Before // 먼저 실행되어서 목 객체 초기화
	public void setup() {
		MockitoAnnotations.initMocks(this); // 목 객체를 초기화한다.
		this.mockMvc = MockMvcBuilders.standaloneSetup(replyController).build(); // 가짜 mvc 객체를 생성한다.
	}

	// 댓글 내용 보기 메소드 테스트
	@Test @Ignore
	public void testGetReply() throws Exception{
		// given : 대전제 - service.getReply(1)이라는 요청을 하게 되면
		// 그러면 응답으로 replyVo 객체가 돌아올 것이다.
		ReplyVo replyVo = new ReplyVo();
		replyVo.setReplyId(2);
		replyVo.setMemberId("test");
		replyVo.setReplyContent("새로운 댓글 내용"); 
		replyVo.setBoardId(1);
		replyVo.setReplyGroup(0);
		replyVo.setReplyOrder(0);
		replyVo.setReplyIndent(0);
		Mockito.when(service.getReply(1)).thenReturn(replyVo);
		
		// when
		// get방식으로 "/reply/detail"라는 요청을 하면서 파라미터로 /reply/detail?replyId=1 을 전달하겠다.
		this.mockMvc.perform(get("/reply/detail").param("replyId", "1"))	
		
		// then
		.andExpect(status().isOk())		// 응답 상태가 200인지 확인한다.
		.andExpect(view().name("reply/replyDetail"))	// replyDetail.jsp로 이동하는지 확인한다.
		.andExpect(model().attributeExists("replyVo"))	// model에 replyVo이 있는지 확인한다.
		.andExpect(model().attribute("replyVo", replyVo));
	}
	
	// 댓글 목록 테스트
	@Test @Ignore
	public void testListReply() throws Exception{
		// given : 대전제 - service.listReply() 이라는 요청을 하게 되면
		// 그러면 응답으로 replyList 객체가 돌아올 것이다.
		ReplyVo replyVo = new ReplyVo();
		replyVo.setReplyId(2);
		replyVo.setMemberId("test");
		replyVo.setReplyContent("새로운 댓글 내용"); 
		replyVo.setBoardId(1);
		replyVo.setReplyGroup(0);
		replyVo.setReplyOrder(0);
		replyVo.setReplyIndent(0);
		
		
		List<ReplyVo> replyList = new ArrayList<>();
		replyList.add(replyVo);
		
		Mockito.when(service.listReply()).thenReturn(replyList);
		
		// when
		// get방식으로 "/reply/list"라는 요청을 한다.
		this.mockMvc.perform(get("/reply/list"))	
		
		// then
		.andExpect(status().isOk())		// 응답 상태가 200인지 확인한다.
		.andExpect(view().name("reply/replyList"))	// replyList.jsp로 이동하는지 확인한다.
		.andExpect(model().attributeExists("listReply"))	// model에 replyList이 있는지 확인한다.
		.andExpect(model().attribute("listReply", replyList));
	}
	
	/**
	 * 컨트롤러에 주입되어 있는 서비스 레이어의 특정 메소드를 테스트한다.
	 * - service.createReply() 메소드 테스트
	 */
	@Test @Ignore
    public void testCreateReply() throws Exception {
        // Given: 테스트를 위한 전제 조건을 설정한다.
        // ReplyVo 객체를 생성하고, service.createReply 메서드가 호출될 때 1을 반환하도록 설정.
        ReplyVo replyVo = new ReplyVo();
        replyVo.setMemberId("test");
        replyVo.setReplyContent("새로운 댓글 내용"); 
        replyVo.setBoardId(2);
		replyVo.setReplyGroup(0);
		replyVo.setReplyOrder(0);
		replyVo.setReplyIndent(0);
        Mockito.when(service.createReply(Mockito.any(ReplyVo.class))).thenReturn(1);

        // When: /createReply라는 요청을 보내는 행동을 함.
        // 요청 시, ReplyVo 객체의 memberId, boardId 파라미터를 함께 전달한다.
        mockMvc.perform(post("/reply/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("memberId", replyVo.getMemberId())
                .param("boardId", String.valueOf(replyVo.getBoardId()))
				.param("replyGroup", String.valueOf(replyVo.getReplyGroup()))
				.param("replyOrder", String.valueOf(replyVo.getReplyOrder()))
				.param("replyIndent", String.valueOf(replyVo.getReplyIndent())))
        
        // Then: When에서 수행한 행동의 결과를 검증. 기대한 결과가 실제로 발생했는지 확인.
        // 그리고 리다이렉트되는 URL이 "/reply/list"인지 확인한다.
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/reply/list")); // 댓글 목록 요청
    }
	
	// 수정 메소드 테스트
	@Test @Ignore
    public void testUpdateReply() throws Exception{
        // Given: 테스트를 위한 전제 조건을 설정합니다.
        // 여기서는 service.updateReply() 호출이 1을 반환하도록 설정합니다.
        ReplyVo replyVo = new ReplyVo();
		replyVo.setReplyId(2);
        replyVo.setMemberId("test");
        replyVo.setBoardId(2);
		replyVo.setReplyGroup(1);
		replyVo.setReplyOrder(1);
		replyVo.setReplyIndent(1);
		replyVo.setReplyContent("수정된 댓글 내용");
        Mockito.when(service.updateReply(Mockito.any(ReplyVo.class))).thenReturn(1);

     // Given: 세션에 로그인된 사용자 정보를 설정합니다.
        MemberVo memberVo = new MemberVo("test"); 
        memberVo.setPassword("1234");  
        memberVo.setEmail("a@a.com");  
        memberVo.setName("test"); 

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("memberVo", memberVo);

        // When: 실제 테스트하는 동작을 수행합니다.
        // 여기서는 /updateReply 엔드포인트에 POST 요청을 수행합니다.
        // 요청 시, ReplyVo 객체의 replyId, memberId, boardId 파라미터를 전달하고, 세션 정보를 포함합니다.    
        mockMvc.perform(post("/reply/update")
             .session(session) // <-- 로그인된 사용자 세션 추가
             .contentType(MediaType.APPLICATION_FORM_URLENCODED)
             .param("replyId", String.valueOf(replyVo.getReplyId()))
             .param("memberId", replyVo.getMemberId())
             .param("boardId", String.valueOf(replyVo.getBoardId()))
			 .param("replyGroup", String.valueOf(replyVo.getReplyGroup()))
			 .param("replyOrder", String.valueOf(replyVo.getReplyOrder()))
			 .param("replyIndent", String.valueOf(replyVo.getReplyIndent())))
        
       // Then: When에서 수행한 행동의 결과를 검증합니다.
        // 기대한 결과가 실제로 발생했는지 확인합니다.
        // 예: 상태 코드가 3xx 리다이렉션인지, 리다이렉트되는 URL이 "/reply/list"인지 확인합니다.
        .andExpect(status().is3xxRedirection())      
        .andExpect(redirectedUrl("/reply/list"));   // 댓글 목록 요청
    }
	
	// 삭제 메소드 테스트
    @Test //@Ignore
    public void testDeleteReply() throws Exception{
     // given
     int replyId = 2;
     Mockito.when(service.deleteReply(replyId)).thenReturn(1);
     
     // when
     mockMvc.perform(post("/reply/delete")
           .contentType(MediaType.APPLICATION_FORM_URLENCODED)
           .param("replyId", String.valueOf(replyId)))
     // then
     // is3xxRedirection() : 응답이 3xx 인지 확인한다. 
     // 메소드가 반환하는 jsp 페이지가 없어요. 대신 다른 컨트롤러를 호출한다.
     .andExpect(status().is3xxRedirection())      
     .andExpect(redirectedUrl("/reply/list"));
  }
}
