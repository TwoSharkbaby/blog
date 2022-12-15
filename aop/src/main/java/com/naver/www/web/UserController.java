package com.naver.www.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.naver.www.domain.CommonDto;
import com.naver.www.domain.JoinReqDto;
import com.naver.www.domain.UpdateReqDto;
import com.naver.www.domain.User;
import com.naver.www.domain.UserRepository;

@RestController
public class UserController {  // 원래 할때는 서비스에서하고 오류는 예외처리에서 따로하고 
								// 컨트롤러는 라우팅 / 데이터반환 또는 파일반환만 처리
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/user")
	public CommonDto<List<User>> findAll() {
		return new CommonDto<>(HttpStatus.OK.value(), userRepository.findAll());
	}
	
	@GetMapping("/user/{id}")
	public CommonDto<User> findById(@PathVariable int id) {
		return new CommonDto<>(HttpStatus.OK.value(), userRepository.findById(id));
	}
	
	@PostMapping("/user")
	public CommonDto save(@RequestBody JoinReqDto dto) {
		userRepository.save(dto);
		return new CommonDto<>(HttpStatus.CREATED.value());
	}
	
//	@PostMapping("/user")
//	public ResponseEntity<String> save(@RequestBody JoinReqDto dto) {
//		userRepository.save(dto);
//		return new ResponseEntity<>(HttpStatus.CREATED);
//	}
	
	
	@DeleteMapping("/user/{id}")
	public CommonDto delete(@PathVariable int id) {
		userRepository.delete(id);
		return new CommonDto<>(HttpStatus.OK.value());
	}
	
	@PutMapping("/user/{id}")
	public CommonDto update(@PathVariable int id, @RequestBody UpdateReqDto dto) {
		userRepository.update(id, dto);
		return new CommonDto<>(HttpStatus.OK.value());
	}
		
	
}
