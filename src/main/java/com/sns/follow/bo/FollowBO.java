package com.sns.follow.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.follow.domain.Follow;
import com.sns.follow.mapper.FollowMapper;

@Service
public class FollowBO {
	
	@Autowired
	private FollowMapper followMapper;
	
	// input : postUserId, userId
	// output : int or x (나는 따로 열을 반환할게 없으니, 그냥 void를 선택)
	public void addFollow(Integer userId, int postUserId) {
		followMapper.insertFollow(userId, postUserId);
	}
	
	
	// input : x
	// output : List<follow>
	public List<Follow> getFollowList() {
		return followMapper.selectFollowList();
	}
}
