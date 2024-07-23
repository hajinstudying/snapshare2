package com.snapshare.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snapshare.web.mapper.BoardMapperInterface;
import com.snapshare.web.mapper.BoardTagMapperInterface;
import com.snapshare.web.mapper.TagMapperInterface;
import com.snapshare.web.vo.BoardDto;
import com.snapshare.web.vo.BoardTagVo;
import com.snapshare.web.vo.BoardVo;
import com.snapshare.web.vo.TagVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 게시물 서비스 클래스
 */

@Slf4j
@Service
public class BoardSerivceImpl implements BoardService {

	@Autowired
	private BoardMapperInterface boardMapper;
	@Autowired
	private TagMapperInterface tagMapper;
	@Autowired
	private BoardTagMapperInterface boardTagMapper;
	
	/**
	 * 게시물 상세 조회 메소드
	 * - 조회수 증가 메소드를 트랜잭션으로 실행
	 * - 해당 게시물의 태그를 함께 가져옴
	 */
	@Override
	@Transactional
	public BoardDto getBoardDto(int boardId) {
		// 게시물 객체 가져오기
		BoardVo boardVo = boardMapper.getBoard(boardId);
		// 해당 게시물 조회수 증가
		boardMapper.incrementHitNo(boardId);
		// 태그 목록 가져오기
		List<String> tagNames = boardTagMapper.getTagsByBoardId(boardId);
		
		//Dto에 담아서 반환
		BoardDto boardDto = new BoardDto();
		// BoardVo의 속성들을 BoardDto에 복사
	    boardDto.setBoardId(boardVo.getBoardId());
	    boardDto.setFileName(boardVo.getFileName());
	    boardDto.setMemberId(boardVo.getMemberId());
	    boardDto.setHitNo(boardVo.getHitNo());
	    // tagList를 BoardDto에 설정
	    boardDto.setTagNames(tagNames);
		
		return boardDto;
	}
	
	/**
	 * 게시물 객체만 반환하는 게시물 상세조회 메소드
	 */
	@Override
	public BoardVo getBoard(int boardId) {
		return boardMapper.getBoard(boardId);
	}
	

	/**
	 * 게시물 목록 조회 메소드
	 */
	@Override
	public List<BoardVo> listBoard() {
		return boardMapper.listBoard();
	}

	/**
	 * 검색어(태그)로 게시물 목록을 받아오는 메소드
	 */
	@Override
	public List<BoardVo> listBoardByTag(String keyword) {
		return boardMapper.listBoardByTag(keyword);
	}
	
	/**
	 * 게시물 등록 메소드
	 */
	@Override
	@Transactional
	public int createBoard(BoardVo boardVo, String tags) {
		log.info("boardServiceImpl의 createBoard()");
		
		// 게시물 등록
		boardMapper.createBoardSelectKey(boardVo);
	    
		// 태그 처리
        if (tags != null && !tags.isEmpty()) {
            String[] tagArray = tags.split(",");
            for (String tagName : tagArray) {
                tagName = tagName.trim();
                if (!tagName.isEmpty()) {
                    // 태그 존재 여부 확인 및 생성
                    TagVo tagVo = tagMapper.getTag(tagName);
                    if (tagVo == null) {
                        // 태그가 존재하지 않으면 생성
                        tagVo = new TagVo();
                        tagVo.setTagName(tagName);
                        tagMapper.createTagSelectKey(tagVo); // 태그 생성
                    }
                    
                    // 게시물과 태그 연결
                    BoardTagVo boardTagVo = new BoardTagVo();
                    boardTagVo.setBoardId(boardVo.getBoardId());
                    boardTagVo.setTagId(tagVo.getTagId());
                    int taggingResult = boardTagMapper.addTagToBoard(boardTagVo);
                    if(taggingResult > 0) {
                    	log.info("boardTagVo : " + boardTagVo.toString());
                    } else {
                    	log.warn("태그 연결 실패, boardTagVo : " + boardTagVo.toString());
                    }
                }
            }
        }
		
		return boardVo.getBoardId();
	}
	
	/**
	 * 게시물 삭제 메소드
	 * - 게시물을 삭제하기 전에 먼저 해당 게시물에 있는 태그관계를 삭제
	 */
	@Transactional
	@Override
	public int deleteBoard(int boardId) {
		// 먼저 해당 boardId의 태그 삭제
		int deletedTagsNum = boardTagMapper.deleteTagsFromBoard(boardId);
		log.info("삭제하려는 게시물id : " + boardId);
		log.info("해당 게시물의 삭제된 태그의 수 : " + deletedTagsNum);
		return boardMapper.deleteBoard(boardId);
	}
}
