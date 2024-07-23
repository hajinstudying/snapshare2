package com.snapshare.web.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.snapshare.web.service.BoardService;
import com.snapshare.web.vo.BoardVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
   
   // 업로드 파일 경로 읽어오기 WEB-INF/config/file.properties
      @Value("${file.path}")
      private String filePath;
      
   @Autowired
   private BoardService boardService;
   
   private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
   
   /**
    * Simply selects the home view to render by returning its name.
    */
   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String home(Locale locale, Model model) {
      
      return "redirect:/board/list";
   }
   
   @GetMapping("/board/admin")
    public String adminPage(Model model) {
      List<BoardVo> boardList = boardService.listBoard();
      model.addAttribute("boardList", boardList);
      model.addAttribute("filePath", filePath);
        return "board/admin"; // This should correspond to /WEB-INF/views/board/admin.jsp or equivalent view
    }
   
}
