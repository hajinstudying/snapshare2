package com.snapshare.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.snapshare.web.vo.BoardVo;

@Mapper
public interface BoardMapperInterface {
	
	// 게시물 상세보기 추상메소드
	public BoardVo getBoard(int boardId);
	// 게시물 조회수 증가 메소드
	void incrementHitNo(int boardId);
	// 게시물 목록보기
	public List<BoardVo> listBoard();
	// 태그로 게시물 검색 메소드
	public List<BoardVo> listBoardByTag(String keyword);
	// 게시물 등록
	public int createBoardSelectKey(BoardVo boardVo); // selectKey 사용 메소드
	// 게시물 삭제
	public int deleteBoard(int boardId);
}
