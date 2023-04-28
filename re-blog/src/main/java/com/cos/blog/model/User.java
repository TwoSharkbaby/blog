package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @DynamicInsert // insert시에 null인 필드를 제외시킴
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	// DB 넘버린 전략에 따라감 오라클이면 시퀸스 MYSQL이면 오토넘버
	private int id;
	
	@Column(nullable = false, length = 200, unique = true)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("0") // 디펄트를 사용하기에는 부적절한 컬럼
	@Enumerated(EnumType.STRING) // db는 롤타입이 없어서 스트링으로 변경
	private RoleType role;
	private String oauth;  // 카카오나 홈페이지, 구글등 접속 경로
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;
	
}
