package com.snapshare.web.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 회원 도메인 클래스 
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberVo {
	private String memberId; // 회원id
	private String password; // 비밀번호
	private String email;	 // 이메일
	private String name;	 // 이름
	 private String role = "user"; // Default role is "user"
	
	// Board 컨트롤러 테스트를 위한 생성자
	public MemberVo(String memberId) {
		this.memberId = memberId;
	}
}
