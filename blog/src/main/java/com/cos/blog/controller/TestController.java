package com.cos.blog.controller;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class TestController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/user")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	@GetMapping("/user/page")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable){
		return userRepository.findAll(pageable).getContent();
	}

	@GetMapping("/user/{id}")
	public User detail(@PathVariable Long id) {
		return userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException(id + "유저는 없습니다");
			}
		});
	}
	
	@Transactional // jap영속성을 이용한 더티체킹 업데이트 방식 / 메소드가 종료되면 transaction이 발동되서 commint을 해주기때문에
	@PutMapping("/user/{id}")  // save를 따로 해주지 않아도 변경이 됨
	public User updateUser(@PathVariable Long id,@RequestBody User user) { // 업데이트시 파라메타에 id가 없으면 insert
		User checkUser = userRepository.findById(id).orElseThrow(() -> {  // 파라메타에 id가 있고 db에 있으면 update 파라메타에 id가 있지만 db에 없을때 insert
			return new IllegalArgumentException("수정에 실패하였습니다");
		});
		checkUser.setPassword(user.getPassword());
		checkUser.setEmail(user.getEmail());
		//return userRepository.save(checkUser);
		return checkUser;
	}
	
	@PostMapping("/join")
	public String join(User user) {
		user.setRole(RoleType.ADMIN);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
	
	@DeleteMapping("/user/{id}")
	public String delete(@PathVariable Long id) {
		try {
			userRepository.deleteById(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "삭제되었습니다";
	}
	
}
