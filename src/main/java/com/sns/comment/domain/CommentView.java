package com.sns.comment.domain;

import com.sns.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;


// 댓글 하나를 가지고 있는 클래스
@ToString
@Data
public class CommentView {
	// 댓글쓴이의 객체까지 들고있는 친구.
	
	// 댓글 1개
	private Comment comment; // 댓글 하나에 대해서 댓글 쓴이가 누구인가?
	
	// 댓글을 쓴 사람은 누구인지? => 댓글 쓴이에 대한 정보가 들어감.
	private UserEntity user;
}
