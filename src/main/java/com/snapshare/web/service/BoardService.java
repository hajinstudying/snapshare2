package com.snapshare.web.service;

import java.util.List;

import com.snapshare.web.vo.BoardDto;
import com.snapshare.web.vo.BoardVo;


/**
 * BoardService 인터페이스
 */
public interface BoardService {
	
	// 태그포함 게시물 상세보기 추상메소드
	BoardDto getBoardDto(int boardId);
	// 게시물 상세보기 추상메소드2
	public BoardVo getBoard(int boardId);
	// 게시물 목록보기 추상메소드
	public List<BoardVo> listBoard();
	// 태그로 게시물 검색 메소드
	public List<BoardVo> listBoardByTag(String keyword);
	// 게시물 등록 추상메소드
	public int createBoard(BoardVo boardVo, String tags);
	// 게시물 삭제 추상메소드
	public int deleteBoard(int boardId);
}
