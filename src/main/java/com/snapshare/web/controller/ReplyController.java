package com.snapshare.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snapshare.web.service.ReplyService;
import com.snapshare.web.vo.MemberVo;
import com.snapshare.web.vo.ReplyVo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/reply")
@Slf4j
public class ReplyController {
    
    @Autowired
    private ReplyService replyService;
    
    /**
     * 댓글 목록 조회
     */
    @GetMapping("/list")
    public ResponseEntity<List<ReplyVo>> listReply() {
        List<ReplyVo> listReply = replyService.listReply();
        return new ResponseEntity<>(listReply, HttpStatus.OK);
    }
    
    /**
     * 댓글 생성
     */
    @GetMapping("/create") 
    @ResponseBody
    public ResponseEntity<ReplyVo> createReply(@RequestBody ReplyVo replyVo, HttpSession session) {
        // 세션에서 사용자 정보 조회
        MemberVo memberVo = (MemberVo) session.getAttribute("memberVo");
        if (memberVo == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 권한 없음
        }
        
        // 세션에서 조회한 사용자를 작성자로 설정
        replyVo.setMemberId(memberVo.getMemberId());

        // 댓글 생성 서비스 호출
        replyService.createReplySelectKey(replyVo);

        // 생성된 댓글을 ResponseEntity에 담아 클라이언트에 반환
        return new ResponseEntity<>(replyVo, HttpStatus.CREATED);
    }
    
    /**
     * 댓글 수정
     */
    @PostMapping("/update")
    public ResponseEntity<ReplyVo> updateReply(@RequestBody ReplyVo replyVo, HttpSession session) {
        // 세션에서 사용자 정보 조회
        MemberVo memberVo = (MemberVo) session.getAttribute("memberVo");
        if (memberVo == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 권한 없음
        }
        // 세션에서 조회한 사용자를 작성자로 설정
        replyVo.setMemberId(memberVo.getMemberId());
        
        replyService.updateReply(replyVo);
        return new ResponseEntity<>(replyVo, HttpStatus.OK);
    }
    
    /**
     * 댓글 삭제
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteReply(@RequestParam("replyId") int replyId, HttpSession session) {
        // 세션에서 사용자 정보 조회
        MemberVo memberVo = (MemberVo) session.getAttribute("memberVo");
        if (memberVo == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 권한 없음
        }
        
        replyService.deleteReply(replyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
