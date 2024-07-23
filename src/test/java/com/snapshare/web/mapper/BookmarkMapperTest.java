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

import com.snapshare.web.vo.BookmarkVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class BookmarkMapperTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private BookmarkMapperInterface bookmarkMapper;

    @Test @Ignore
    public void testDataSource() {
        try (Connection conn = dataSource.getConnection()) {
            assertNotNull(conn);
            log.info("획득한 커넥션 : " + conn);
        } catch (Exception e) {
            log.error("데이터베이스 연결 실패", e);
        }
    }

    @Test @Ignore
    public void testBookmarkMapper() {
        assertNotNull(bookmarkMapper);
        log.info("bookmarkMapper 객체 : " + bookmarkMapper);
    }

    @Test @Ignore
    public void testGetBookmark() {
        int bookmarkId = 1;
        BookmarkVo bookmarkVo = bookmarkMapper.getBookmark(bookmarkId);
        assertNotNull(bookmarkVo);
        log.info("조회된 북마크 : " + bookmarkVo);
    }

    @Test @Ignore
    public void testListBookmark() {
        List<BookmarkVo> bookmarkList = bookmarkMapper.listBookmark();
        assertNotNull(bookmarkList);
        assertTrue(bookmarkList.size() > 0);
        bookmarkList.forEach(bookmark -> log.info("북마크 : " + bookmark));
    }

    @Test @Ignore
    public void testInsertBookmark() {
        BookmarkVo bookmarkVo = new BookmarkVo();
        bookmarkVo.setBookmarkId(10); // 예시로 사용할 북마크 ID
        bookmarkVo.setMemberId("test");
        bookmarkVo.setBoardId(5); // 예시로 사용할 게시판 ID

        int result = bookmarkMapper.insertBookmark(bookmarkVo);
        assertTrue(result > 0);
        log.info("북마크 추가 결과 : " + result);
    }

    

    @Test @Ignore
    public void testUpdateBoardBookmarkCount() {
        String memberId = "test";
        int boardId = 5; // 해당 게시판의 ID

        int result = bookmarkMapper.updateBoardBookmarkCount(memberId, boardId);
        assertTrue(result > 0);
        log.info("게시판 북마크 카운트 업데이트 결과 : " + result);
    }
    
    @Test //@Ignore
    public void testDeleteBookmark() {
        int bookmarkId = 4;

        int result = bookmarkMapper.deleteBookmark(bookmarkId);
        assertTrue(result > 0);
        log.info("북마크 삭제 결과 : " + result);
    }
}
