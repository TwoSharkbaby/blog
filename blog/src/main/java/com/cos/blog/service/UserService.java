package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 

	@Transactional
	public User findUser(String username) {
		User user = userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});
		return user;
	}
	
	
	@Transactional
	public void signUp(User user) {
		String rawPassword = user.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	// 수정할때는 영속성 컨텐스트 user오브젝트를 영속화시키고 user오브젝트를 수정해서 트렌젝션 응용
	@Transactional   // select해서 가져온 영속성 컨택스트에 있는 user를 변경하면 자동으로 db로 update됨
	public void update(User user) {  
		User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원찾기 실패");
		});
		if(persistance.getOauth() == null || persistance.getOauth().equals("")) {  // api 공경 방지 ( validate check )
			String rawPassword = user.getPassword();
			String encPassword = passwordEncoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}
	}

}
