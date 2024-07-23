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
		int count = likeMapper.selectlikeCountByPostIdUserId(postId, userId);
		
		// 여부 => 삭제 or 추가
		if(count > 0) {
			likeMapper.deletelikeToggle(postId, userId);
		} else {
			likeMapper.insertlikeToggle(postId, userId);
		}
	}
	
	public int generateLikeCountByPostId(int postId) {
		return likeMapper.selectlikeCountByPostId(postId); 
	}
	
	public boolean booleanLikeByPostIdUserId(int postId, int userId) {
		int heart = likeMapper.booleanLikeByPostIdUserId(postId, userId);
		if(heart > 0) {
			return true;
		} else {
			return false;
		}
	}
}
