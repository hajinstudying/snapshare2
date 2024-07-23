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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.snapshare.web.service.BookmarkService;
import com.snapshare.web.vo.BookmarkVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@WebAppConfiguration
public class BookmarkControllerTest {

    @Mock
    private BookmarkService bookmarkService;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @InjectMocks
    private BookmarkController bookmarkController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookmarkController).build();
    }

    @Test @Ignore
    public void testGetBookmark() throws Exception {
        int bookmarkId = 1;
        BookmarkVo bookmarkVo = new BookmarkVo(bookmarkId, "Test Bookmark", 1, "1.jpg");
        Mockito.when(bookmarkService.getBookmark(bookmarkId)).thenReturn(bookmarkVo);

        this.mockMvc.perform(get("/bookmark/detail")
                .param("bookmarkId", String.valueOf(bookmarkId)))
                .andExpect(status().is3xxRedirection())  // 리다이렉션 상태 확인
                .andExpect(redirectedUrl("/board/detail?boardId=" + bookmarkVo.getBoardId()));  
    }


    @Test @Ignore
    public void testListBookmark() throws Exception {
        BookmarkVo bookmarkVo1 = new BookmarkVo(1, "Java", 1, "1.jpg");
        BookmarkVo bookmarkVo2 = new BookmarkVo(2, "Spring", 2, "1.jpg");
        List<BookmarkVo> bookmarkList = new ArrayList<>();
        bookmarkList.add(bookmarkVo1);
        bookmarkList.add(bookmarkVo2);

        Mockito.when(bookmarkService.listBookmark()).thenReturn(bookmarkList);

        this.mockMvc.perform(get("/bookmark/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("bookmark/list"))
                .andExpect(model().attributeExists("bookmarkList"))
                .andExpect(model().attribute("bookmarkList", bookmarkList));
    }

    @Test @Ignore
    public void testCreateBookmark() throws Exception {
        BookmarkVo bookmarkVo = new BookmarkVo();
        bookmarkVo.setMemberId("test");
        bookmarkVo.setBoardId(1);

        Mockito.when(bookmarkService.insertBookmark(bookmarkVo)).thenReturn(1);

        this.mockMvc.perform(post("/bookmark/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("memberId", bookmarkVo.getMemberId())
                .param("boardId", String.valueOf(bookmarkVo.getBoardId())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bookmark/list"));
    }

    @Test @Ignore
    public void testUpdateBoardBookmarkCount() throws Exception {
        String memberId = "test";
        int boardId = 1;

        Mockito.when(bookmarkService.updateBoardBookmarkCount(memberId, boardId)).thenReturn(1);

        this.mockMvc.perform(post("/bookmark/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("memberId", memberId)
                .param("boardId", String.valueOf(boardId)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bookmark/list"));
    }

    @Test @Ignore
    public void testDeleteBookmark() throws Exception {
        int bookmarkId = 1;

        Mockito.when(bookmarkService.deleteBookmark(bookmarkId)).thenReturn(1);

        this.mockMvc.perform(post("/bookmark/delete")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("bookmarkId", String.valueOf(bookmarkId)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bookmark/list"));
    }
}
