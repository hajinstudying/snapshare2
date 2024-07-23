package com.snapshare.web.mapper;

import java.util.List;

import com.snapshare.web.vo.TagVo;

/**
 * 태그 매퍼 인터페이스
 */
public interface TagMapperInterface {

	// 태그 개별 조회 추상메소드
	public TagVo getTag(String tagName);
	// 태그 생성 추상메소드
	public int createTagSelectKey(TagVo tagVo);
	// 태그 삭제 추상메소드
	public int deleteTag(int tagId);
	// 태그목록 조회 메소드
	public List<TagVo> listTag();
}
