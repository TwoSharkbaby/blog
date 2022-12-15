package com.naver.www.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	public List<User> findAll(){
		List<User> users = new ArrayList<>();
		users.add(new User(1,"kim","1234","010-0000-0000"));
		users.add(new User(2,"jang","4321","010-1111-0000"));
		users.add(new User(3,"jeong","4567","010-2222-0000"));
		return users;
	}
	
	public User findById(int id) {
		return new User(1,"kim","1234","010-0000-0000");
	}
	
	public void save(JoinReqDto dto) {
		System.out.println("DB에 INSERT됨");
	}
	
	public void delete(int id) {
		System.out.println("DB에 DELETE됨");
	}
	
	public void update(int id, UpdateReqDto dto) {
		throw new IllegalArgumentException("오마니 아바지");
	}
}
