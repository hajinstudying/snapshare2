package com.snapshare.web.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 게시물-태그 도메인 클래스
 * - 게시물과 태그 테이블의 다대다 관계 해소를 위한 중간단계 클래스
 * - 게시물과 태그 관계를 나타냅니다.
 * - 게시물에 태그 등록 시
 *   1) Tag테이블에서 태그가 존재하는지 확인
 *   2) 없다면 Tag 테이블에 새 태그 삽입
 *   3) boardTag 테이블로 태그와 게시물의 연결  
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardTagVo {
	
	private int tagId; 		// 태그id
	private int boardId;	// 게시물id
	
}
