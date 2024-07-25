package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostBO {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private CommentBO commentBO;
	
	// input : X
	// output : List<PostEntity> 
	public List<PostEntity> getPostEntityList() {
		return postRepository.findByOrderByIdDesc(); //findBy orderBy Id Desc
	}
	
	// input : content, file
	// output : PostEntity
	
	public PostEntity addPost(int userId, String userLoginId, String content, MultipartFile file) {
		
		String imagePath = fileManagerService.uploadFile(file, userLoginId);
		
		// 무조건 이미지가 있으니확인.
		
		return postRepository.save(
				PostEntity.builder()
				.userId(userId)
				.content(content)
				.imagePath(imagePath)
				.build());
	}
	
	// input : postId, userId
	// output : X 그냥 친구를 삭제하는 것이니까.
	
	@Transactional // 트랜젝션 : 어떤 일을 수행하는데 사용하는 묶음 글을 지우면서 여러가지 일들을 지워야하니, 은행사이트에서 조회도 해보고, 일의 단위들을 묶어서 트랜젝션
	public void deletePost(int postId, int userId) {
		// 그친구를 찾아오는것
		PostEntity post = postRepository.findByIdAndUserId(postId, userId);
		
		// 내가 삭제할 대상을 찾음.
		if(post == null) {
			log.info("[글 삭제] post is null. postId:{}, userId:{}", postId, userId);
			return;
		}
		
		postRepository.deleteById(postId); // 지워진 친구
			// like, comment를 삭제하고 imagePath를 삭제하는게 맞다.
		fileManagerService.deleteFile(post.getImagePath());
			
		likeBO.deleteByPostId(postId);
			
		commentBO.deleteCommentByPostId(postId);
		
	}
}
