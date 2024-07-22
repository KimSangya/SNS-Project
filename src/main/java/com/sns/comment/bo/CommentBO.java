package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentView;
import com.sns.comment.mapper.CommentMapper;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@Service
public class CommentBO {
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserBO userBO;
	
	public List<Comment> getCommentList() {
		return commentMapper.selectCommentList();
	}
	
	// input : postId, userId, content
	// output : x
	public void addComment(int postId, int userId, String content) {
		commentMapper.insertComment(postId, userId, content);
	}
	
	// input : 글 번호
	// output : List<CommentView> 글에 해당하는 댓글을 가져오는 것이다.
	
	public List<CommentView> generateCommentViewListByPostId(int postId) {
		List<CommentView> commentViewList = new ArrayList<>();
		// commentMapper.selectCommentList(postId); // 최종적으로 리턴되는 친구
		
		// 댓글들을 가져옴
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		
		// 반복문 순회 => Comment => CommentView => List에 담음
		for(Comment comment : commentList) {
			CommentView commentView = new CommentView();
			
			// 댓글 1개
			commentView.setComment(comment);
			// 댓글 쓴이
			UserEntity user = userBO.getUserEntityById(comment.getUserId());
			commentView.setUser(user);
			
			commentViewList.add(commentView);
		}
		return commentViewList;
	}
	
	public void deleteComment(int commentId) {
		commentMapper.deleteComment(commentId);
	}
}
