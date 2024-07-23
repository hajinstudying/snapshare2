package com.snapshare.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snapshare.web.mapper.TagMapperInterface;
import com.snapshare.web.vo.TagVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 태그 서비스 클래스
 */

@Slf4j
@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagMapperInterface tagMapper;
	
	/**
	 * 태그 목록 조회 메소드
	 */
	@Override
	public List<TagVo> listTag() {
		return tagMapper.listTag();
	}

	/**
	 * 태그 등록 메소드
	 * - 동일한 이름의 tagname이 있을 경우 등록 취소
	 */
	@Override
	@Transactional
	public int createTag(TagVo tagVo) {
		// 동일한 이름의 태그가 있는지 확인
		String tagName = tagVo.getTagName();
		if(tagMapper.getTag(tagName) != null) {
			// 이미 동일한 이름의 태그가 존재하면 등록 취소
	        log.warn("동일한 이름의 태그가 이미 존재합니다: " + tagName);
	        return 0;
		}		
		int result = tagMapper.createTagSelectKey(tagVo);
		log.info("등록한 태그의 tagId : " + tagVo.getTagId());
		return result;
	}
	
	/**
	 * 태그 삭제 메소드
	 */
	@Override
	public int deleteTag(int tagId) {
		return tagMapper.deleteTag(tagId);
	}

}
