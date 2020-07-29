package com.magicwater.springbook.service;

import java.util.ArrayList;
import java.util.List;

import com.magicwater.springbook.domain.Board;
import com.magicwater.springbook.persistance.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;

	@Override
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}

	@Override
	public void updateBoard(Board board) {
		Board findBoard = boardRepo.findById(board.getSeq()).get();

		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepo.save(findBoard);

	}

	@Override
	public void deleteBoard(Board board) {

		boardRepo.deleteById(board.getSeq());

	}

	@Override
	public Board getBoard(Board board) {

		return boardRepo.findById(board.getSeq()).get();
	}

	@Override
	public Page<Board> getBoardList(Board board) {
		// Pageable 에서 PageRequest로 수정함
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "seq");
		return boardRepo.getBoardList(pageable);
	}

	@Override
	public List<Board> findByTitleContaingIgnoreCase(@Param("title") String keyword) {
		List<Board> board = boardRepo.findByTitleContaingIgnoreCase(keyword);
		List<Board> result = new ArrayList<>();

		if(board.isEmpty()){
			return result;
		}

		for(Board tempBoard : board){
			result.add(tempBoard);
		}

		return result;
	}
    

    
}