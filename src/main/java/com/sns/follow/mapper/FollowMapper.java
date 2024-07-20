package com.sns.follow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sns.follow.domain.Follow;

@Mapper
public interface FollowMapper {
	
	public void insertFollow(
			@Param("fromUser") Integer userId, // 팔로우를 하는 사람
			@Param("toUser") int postUserId); // 팔로우를 당하는 사람
	
	public List<Follow> selectFollowList();
}
