package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void register(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true)
	public Page<Board> list(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board getDetail(Long id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패");
		});
	}
	
	@Transactional
	public void delete(Long id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional  // 더티체킹을 이용한 업데이트
	public void update(Long id, Board board) {
		Board temBoard = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 수정 실패");
		});  // 영속화 완료
		temBoard.setTitle(board.getTitle());
		temBoard.setContent(board.getContent());
	}

}
