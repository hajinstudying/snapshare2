package com.snapshare.web.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 태그 도메인 클래스
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TagVo {
	
	private int tagId; 		// 태그id
	private String tagName; // 태그명
	
	// 태그 생성을 위한 생성자
	public TagVo (String tagName) {
		this.tagName = tagName;
	}
}
