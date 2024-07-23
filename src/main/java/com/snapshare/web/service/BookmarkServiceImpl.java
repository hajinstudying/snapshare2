package com.snapshare.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snapshare.web.mapper.BookmarkMapperInterface;
import com.snapshare.web.vo.BookmarkVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookmarkServiceImpl implements BookmarkService {

    @Autowired
    private BookmarkMapperInterface bookmarkMapper;

    @Override
    public BookmarkVo getBookmark(int bookmarkId) {
        return bookmarkMapper.getBookmark(bookmarkId);
    }

    @Override
    public List<BookmarkVo> listBookmark() {
        return bookmarkMapper.listBookmark();
    }

    @Override
    @Transactional
    public int insertBookmark(BookmarkVo bookmarkVo) {
        return bookmarkMapper.insertBookmark(bookmarkVo);
    }
    
    @Override
    @Transactional
    public int updateBoardBookmarkCount(String memberId, int boardId) {
        return bookmarkMapper.updateBoardBookmarkCount(memberId, boardId);
    }

    @Override
    @Transactional
    public int deleteBookmark(int bookmarkId) {
        return bookmarkMapper.deleteBookmark(bookmarkId);
    }

    @Override
    public List<BookmarkVo> getBookmarkedBoards() {
        return bookmarkMapper.getBookmarkedBoards();
    }

	@Override
	public boolean checkDuplicateBookmark(int boardId, String memberId) {
		return false;
	}
}

