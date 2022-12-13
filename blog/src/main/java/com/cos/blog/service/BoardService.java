package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;

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
	public void delete(Long id) {  // 다른 아이디가 삭제 요청하면 되지 않게 막아야됨
		boardRepository.deleteById(id); // 서비스와 jsp에서
	}
	
	@Transactional  // 더티체킹을 이용한 업데이트
	public void update(Long id, Board board) {
		Board temBoard = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 수정 실패");
		});  // 영속화 완료
		temBoard.setTitle(board.getTitle());
		temBoard.setContent(board.getContent());
	}
	
	@Transactional
	public void replyRegister(ReplySaveRequestDto replySaveRequestDto) {
		replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
	}
	
	@Transactional
	public void replyDelete(Long replyId) {
		replyRepository.deleteById(replyId);
	}

}
