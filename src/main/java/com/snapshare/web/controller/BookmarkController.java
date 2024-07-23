package com.snapshare.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snapshare.web.service.BookmarkService;
import com.snapshare.web.vo.BookmarkVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/bookmark")
@Slf4j
public class BookmarkController {
	// 업로드 파일 경로 읽어오기 WEB-INF/config/file.properties
		@Value("${file.path}")
		private String filePath;

    private final BookmarkService bookmarkService;

    @Autowired
    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }
    @GetMapping("/detail")
    public String getBookmark(@RequestParam("boardId") int boardId, Model model) {
        BookmarkVo bookmarkVo = bookmarkService.getBookmark(boardId);
        if (bookmarkVo == null) {
            // 북마크가 존재하지 않는 경우 처리
            return "redirect:/error"; // 예를 들어, 에러 페이지로 리다이렉트
        }
        return "redirect:/board/detail?boardId=" + bookmarkVo.getBoardId();
    }


    @GetMapping("/list")
    public String listBookmarks(Model model) {
        List<BookmarkVo> bookmarkList = bookmarkService.listBookmark();
        model.addAttribute("bookmarkList", bookmarkList);
        model.addAttribute("filePath", filePath);
        return "bookmark/bookmarklist";
    }


    @PostMapping("/create")
    public String insertBookmark(@RequestParam("boardId") Integer boardId,
                                 @RequestParam("memberId") String memberId) {
        try {
            // 요청 데이터 검증
            if (boardId == null || memberId == null || memberId.isEmpty()) {
                log.info("북마크 생성 실패: boardId 또는 memberId가 유효하지 않습니다.");
                return "redirect:/login";
            }

            log.info("북마크 추가 요청 - boardId: {}, memberId: {}", boardId, memberId);

            // 이미 북마크가 있는지 확인
            boolean isDuplicate = bookmarkService.checkDuplicateBookmark(boardId, memberId);
            if (isDuplicate) {
                log.warn("이미 등록된 북마크입니다 - boardId: {}, memberId: {}", boardId, memberId);
                return "redirect:/bookmark/list";
            }

            // BookmarkVo 객체 생성
            BookmarkVo bookmarkVo = new BookmarkVo();
            bookmarkVo.setBoardId(boardId);
            bookmarkVo.setMemberId(memberId);

            // 북마크 생성 서비스 호출
            int result = bookmarkService.insertBookmark(bookmarkVo);

            if (result > 0) {
                log.info("북마크가 추가되었습니다 - boardId: {}, memberId: {}", boardId, memberId);
                return "redirect:/bookmark/list"; // 북마크가 성공적으로 추가되면 북마크 리스트 페이지로 리다이렉트
            } else {
                log.warn("북마크 추가 실패 - boardId: {}, memberId: {}", boardId, memberId);
                return "redirect:/home"; // 실패 시 홈 페이지로 리다이렉트
            }
        } catch (Exception e) {
            log.error("북마크 추가 중 오류 발생", e);
            return "redirect:/home"; // 예외 발생 시 홈 페이지로 리다이렉트
        }
    }

    @PostMapping("/update")
    public String updateBoardBookmarkCount(@RequestParam("memberId") String memberId,
                                           @RequestParam("boardId") int boardId) {
        bookmarkService.updateBoardBookmarkCount(memberId, boardId);
        return "redirect:/bookmark/bookmarklist";
    }

    @PostMapping("/delete")
    @ResponseBody
    public String deleteBookmark(@RequestParam("bookmarkId") int bookmarkId) {
        bookmarkService.deleteBookmark(bookmarkId);
        return "redirect:/bookmark/bookmarklist";
    }
}