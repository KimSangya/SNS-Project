package com.sns.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {
	
	// 조회해서 내용이 있는지 확인하는 친구
	public int selectlikeCountByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	// 집어 넣는 친구
	public void insertlikeToggle(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public void deletelikeToggle(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public int selectlikeCountByPostId(int postId);
	
	public int booleanLikeByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
}
