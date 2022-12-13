package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob  // 대용량 데이터
	private String content;
	
	private int count;
	
	@ManyToOne(fetch = FetchType.EAGER)  // many = board / one = user
	@JoinColumn(name = "userId")  // fetch타입 eager은 board가 사용될때 같이 사용 / lazy는 필요할때만 불러옴
	private User user;
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // mappedBy 연관간계의 주인이 아니다 fk키가 아니기때문에 컬럼생성x
	@JsonIgnoreProperties({"board"})  // 무한참조 방지  // cascade remove 보드를 삭제할때 참조하는 덧글도 다 삭제하기
	@OrderBy("id desc")  // 내림차순
	private List<Reply> reply;  // 가져올 데이터가 many기 때문에 필요할때만 불러오기 위함
	
	@CreationTimestamp
	private Timestamp createDate;
}
