package com.sns.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {
	
	// 조회해서 내용이 있는지 확인하는 친구
	// 이 부르고 있는 사이즈 들을 ctrl + alt + H
	 /*public int selectlikeCountByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public int selectlikeCountByPostId(int postId); */
	
	// 카운트 쿼리를 하나로 합친다.
	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId,
			@Param("userId") Integer userId);
	
	// 집어 넣는 친구
	public void insertlikeToggle(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public void deletelikeToggle(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public int filledLikeByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public void deleteByPostId(int postId);
}
