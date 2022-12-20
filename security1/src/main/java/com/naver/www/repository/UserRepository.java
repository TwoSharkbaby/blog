package com.naver.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naver.www.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User findByUsername(String username);
	
}
