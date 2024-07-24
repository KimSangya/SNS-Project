package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.CommentView;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.domain.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@Service
public class TimelineBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	// input : X
	// output : List<CardView>
	public List<CardView> generateCardViewList(Integer userId) { // int로 받는 순간 비로그인이라면 null값을 받지 못하기 때문에, 따로 설정을 해줘야한다.
		
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글 목록을 가져온다. List<PostEntity>
		List<PostEntity> postList = postBO.getPostEntityList();
		
		// 글목록 반복문 순회
		// PostEntity => CardView
		for(PostEntity post : postList) {
			CardView card = new CardView();
			card.setPost(post);
			
			// 글쓴이 명을 가져오기 위해서 가져오는 것.
			// postEntity.getUserId; // 글쓴이 번호
			// index로 걸려있는게 좋은 쿼리다.
			UserEntity user =  userBO.getUserEntityById(card.getPost().getUserId());
			card.setUser(user);
			
			// 댓글 n개를 가지고 와져야한다.
			List<CommentView> commentViewList = commentBO.generateCommentViewListByPostId(post.getId());
			card.setCommentList(commentViewList);
			
			// 좋아요 개수를 리턴을 해주게 된다.
			int likeCount = likeBO.getLikeCountByPostId(post.getId());
			card.setLikeCount(likeCount);
			
			
			//내가 이 친구를 눌렀는지 안눌렀는지에 대해서 확인
//			if(userId != null) {
//				boolean filled = likeBO.booleanLikeByPostIdUserId(post.getId(), userId);
//				card.setFilledLike(filled);
//				cardViewList.add(card);
//			} else {
//				cardViewList.add(card);
//			}
			// !!!반드시 리스트에 넣는다.
			
			// 하트가 채워지는지 안채워지는지에 대한 코드
			card.setFilledLike(likeBO.filledLikeByPostIdUserId(post.getId(), userId));
			
			cardViewList.add(card);
		}
		// -> cardViewList에 꽂아넣는 행위가 필요로하다.
		
		return cardViewList;
	}
}
