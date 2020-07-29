package com.magicwater.springbook.persistance;

import java.util.List;

import com.magicwater.springbook.domain.Board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {


    @Query("SELECT p FROM Board p WHERE p.title LIKE %:title%")
    List<Board> findByTitleContaingIgnoreCase(@Param("title") String searchKeyword);

    @Query("SELECT p FROM Board p WHERE p.content LIKE %:content%")
    List<Board> findByContentContainingIgnoreCase(String content);


    @Query("SELECT b from Board b")
    Page<Board> getBoardList(Pageable pageable);
}