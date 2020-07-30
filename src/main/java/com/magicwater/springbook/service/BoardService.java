package com.magicwater.springbook.service;

import java.util.List;

import com.magicwater.springbook.domain.Board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;


public interface BoardService {
    
    void insertBoard(Board board);
    void updateBoard(Board board);
    void deleteBoard(Board board);
    Board getBoard(Board board);
    Page<Board> getBoardList(Pageable pageable/*Board board*/);
    List<Board> findByTitleContaingIgnoreCase(@Param("title") String searchKeyword);
}