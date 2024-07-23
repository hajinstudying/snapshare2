package com.snapshare.web.service;

import java.util.List;

import com.snapshare.web.vo.TagVo;


/**
 * TagService 인터페이스
 */
public interface TagService {
	
	// 태그 목록보기 추상메소드
	public List<TagVo> listTag();
	// 태그 등록 추상메소드
	public int createTag(TagVo tagVo);
	// 태그 삭제 추상메소드
	public int deleteTag(int tagId);
}
