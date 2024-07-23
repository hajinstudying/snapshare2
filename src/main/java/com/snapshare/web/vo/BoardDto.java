package com.snapshare.web.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 게시물 상세 조회에서 사용되는 BoardDto
 * - BoardVo를 상속받고 태그 리스트를 속성으로 가진다.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardDto extends BoardVo {
	
	// BoardVo의 속성들
	
	// 전달할 추가 속성
	private List<String> tagNames;

}
