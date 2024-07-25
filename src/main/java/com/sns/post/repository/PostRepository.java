package com.sns.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.post.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
	public List<PostEntity> findByOrderByIdDesc();
	
	public PostEntity findByIdAndUserId(int postId, int userId);
	
	public void deleteById(int postId);
}
