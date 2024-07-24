package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.mapper.LikeMapper;

@Service
public class LikeBO {

	@Autowired
	private LikeMapper likeMapper;
	
	// input : postId
	// output : X
	
	public void likeToggle(int postId, int userId) {
		// 있으면 확인을 하고, 만약 있다면 삭제를 하고 없다면 추가를 해주는 로직
		// 조회
		int count = likeMapper.selectLikeCountByPostIdOrUserId(postId, userId);
		
		// 여부 => 삭제 or 추가
		if(count > 0) {
			likeMapper.deletelikeToggle(postId, userId);
		} else {
			likeMapper.insertlikeToggle(postId, userId);
		}
	}
	
	// input : postId
	// output : count(*)
	// 행의 개수를 리턴하고 TimelineBO측에서 올라가게 된다.
	
	// input : postId, userId
	// output : int
	public int getLikeCountByPostId(int postId) {
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, null); 
	}
	
	
	// 좋아요 채울지 말지에 대한 방식
	// input : postId(필수), userId(로그인/비로그인)		output : boolean (채울지에 대한 여부) 
	public boolean filledLikeByPostIdUserId(int postId, Integer userId) {
		// db조회 없이 채우지 않음
		if(userId == null) { 
			return false;
		} 
		// 로그인이면 1. 행이있으면(1) true 2. 없으면(0) false
		return  likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) == 1 ? true : false;
	}
}
