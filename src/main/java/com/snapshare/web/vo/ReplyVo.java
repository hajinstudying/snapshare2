package com.snapshare.web.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReplyVo {
	
	private int replyId; 	 // 댓글id
	private int boardId; 	 // 게시물id
	private String memberId; // 회원id
	private String replyContent; // 댓글 내용
	private int replyGroup;  // 게시물 그룹
	private int replyOrder;  // 그룹내 정렬순서
	private int replyIndent; // 들여쓰기
}
