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

import com.snapshare.web.vo.BookmarkVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 단위테스트
 * - SpringJUnit4ClassRunner 의존성이 안들어오는 경우 pom.xml에 spring-test 확인할것.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Slf4j	// import lombok.extern.slf4j.Slf4j;
public class BookmarkServiceTest {
	 
	// BookmarkService 인터페이스 의존성 주입
	// 인터페이스 타입으로 의존성 주입했지만 실제로는 그 자식인 BookmarkServiceImpl 주입됨.
	@Autowired
	private BookmarkService bookmarkService;
	
	@Test @Ignore
	public void testBookmarkService() {
		assertNotNull(bookmarkService);
		log.info("bookmarkService 인터페이스 : " + bookmarkService); // BookmarkServiceImpl@7063686f
	}

 @Test @Ignore
    public void testGetBookmark() {
        int bookmarkId = 1;
        BookmarkVo bookmarkVo = bookmarkService.getBookmark(bookmarkId);
        assertNotNull(bookmarkVo);
        log.info("조회된 북마크 : " + bookmarkVo);
    }

    @Test @Ignore
    public void testListBookmark() {
        List<BookmarkVo> bookmarkList = bookmarkService.listBookmark();
        assertNotNull(bookmarkList);
        assertTrue(bookmarkList.size() > 0);
        bookmarkList.forEach(bookmark -> log.info("북마크 : " + bookmark));
    }

    @Test @Ignore
    public void testInsertBookmark() {
        BookmarkVo newBookmark = new BookmarkVo();
        newBookmark.setMemberId("test");
        newBookmark.setBoardId(1);
        
        int result = bookmarkService.insertBookmark(newBookmark);
        assertTrue(result > 0);
        log.info("새로 추가된 북마크의 ID: " + newBookmark.getBookmarkId());
    }

    @Test @Ignore
    public void testUpdateBoardBookmarkCount() {
    	String memberId = "test";
    	int boardId = 1;
    	
    	int result = bookmarkService.updateBoardBookmarkCount(memberId, boardId);
    	assertTrue(result > 0);
    	log.info("북마크 추가 및 조회수 업데이트 결과 : " + result);
    }
    
    @Test @Ignore
    public void testDeleteBookmark() {
        int bookmarkId = 1;

        int result = bookmarkService.deleteBookmark(bookmarkId);
        assertTrue(result > 0);
        log.info("북마크 삭제 결과 : " + result);
    }

}