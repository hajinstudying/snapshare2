package com.snapshare.web.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 게시물 도메인 클래스
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class BoardVo {

	private int boardId;		// 게시물id
	private String memberId;	// 회원id
	private String fileName;	// 이미지 첨부파일 이름
	private Date regDate; 		// 게시물 생성날짜
	private int hitNo;			// 조회수
	
	// 페이징 관련 속성(필드, 멤버변수)
	private String pageNum = "1";        // 요청 페이지번호(기본값 1)
	private Integer listCount = 10;      // 한 페이지에 보여줄 게시물갯수
	private Integer pagerPerBlock = 10;  // 한 화면에 보여질 페이지 번호 갯수
	
	// 게시물 생성시 사용하는 생성자
	public BoardVo(String memberId, String fileName) {
		this.memberId = memberId;
		this.fileName = fileName;
	}
	
	// 컨트롤러 테스트에서 사용할 생성자
	public BoardVo(int boardId, String memberId, String fileName) {
		this.boardId = boardId;
		this.memberId = memberId;
		this.fileName = fileName;
	}
}
