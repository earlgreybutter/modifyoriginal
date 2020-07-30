package com.magicwater.springbook.controller;

import java.util.List;

import com.magicwater.springbook.domain.Board;
import com.magicwater.springbook.security.SecurityUser;
import com.magicwater.springbook.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
    
    @Autowired
    private BoardService boardService;

    @GetMapping("/getBoardList")
    @ResponseBody
    public ModelAndView getBoardList( @PageableDefault Pageable pageable, Model model){

        Page<Board> boardList = boardService.getBoardList(pageable);
        model.addAttribute("boardList", boardList);

        log.debug("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}",
                boardList.getTotalElements(), boardList.getTotalPages(), boardList.getSize(),
                boardList.getNumber(), boardList.getNumberOfElements());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("board/getBoardList");
        return modelAndView;
    }

    @PostMapping(value = "/getBoardList")
    public ModelAndView search(@RequestParam(value="keyword") String keyword, Model model){
        List<Board> boardList = boardService.findByTitleContaingIgnoreCase(keyword);    // paging 을 했더니 search 가 안되더라는 의미이다. 
        model.addAttribute("boardList", boardList);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("board/getBoardList");
        return modelAndView;
    }

    @GetMapping("/getBoard")
    public ModelAndView getBoard(Board board, Model model){
        model.addAttribute("board", boardService.getBoard(board));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("board/getBoard");
        return modelAndView;
    }

    // [글등록] 링크 클릭시, 화면 전환만 담당
    @GetMapping("/insertBoard")
    public ModelAndView insertBoardView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("board/insertBoard");
        return modelAndView;
    }    

    // 실질적인 글 등록 처리 
    @PostMapping("/insertBoard")
    public ModelAndView insertBoard(Board board, @AuthenticationPrincipal SecurityUser principal){
        
        board.setMember(principal.getMember());
        boardService.insertBoard(board);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:getBoardList");
        return modelAndView;
    }

    @PostMapping(value="/updateBoard")
    public ModelAndView updateBoard(Board board) {
        boardService.updateBoard(board);
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:getBoardList");
        return modelAndView;
    }
    
    @GetMapping(value="/deleteBoard")
    public ModelAndView deleteBoard(Board board) {
        
        boardService.deleteBoard(board);
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:getBoardList");
        return modelAndView;
    }
    
   
}
