package com.snapshare.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.snapshare.web.vo.BookmarkVo;

public interface BookmarkService {
    public BookmarkVo getBookmark(int bookmarkId);
    public List<BookmarkVo> listBookmark();
    public int insertBookmark(BookmarkVo bookmarkVo);
    public int deleteBookmark(int bookmarkId);
    public int updateBoardBookmarkCount(@Param("memberId") String memberId, @Param("boardId") int boardId);
    public List<BookmarkVo> getBookmarkedBoards();
 // 북마크 중복 체크 메서드
    public boolean checkDuplicateBookmark(int boardId, String memberId);
    }


