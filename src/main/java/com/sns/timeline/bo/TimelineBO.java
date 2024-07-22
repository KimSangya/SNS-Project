package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.CommentView;
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
	
	// input : X
	// output : List<CardView>
	public List<CardView> generateCardViewList() {
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글 목록을 가져온다. List<PostEntity>
		List<PostEntity> postList = postBO.getPostEntityList();
		
		// 글목록 반복문 순회
		// PostEntity => CardView
		for(PostEntity post : postList) {
			CardView card = new CardView();
			card.setPost(post);
			
			// 글쓴이 명을 가져오기 위해서 가져오는 것.
			int userId = card.getPost().getUserId(); // postEntity.getUserId; // 글쓴이 번호
			// index로 걸려있는게 좋은 쿼리다.
			UserEntity user =  userBO.getUserEntityById(userId);
			card.setUser(user);
			
			// 댓글 n개를 가지고 와져야한다.
			List<CommentView> commentViewList = commentBO.generateCommentViewListByPostId(post.getId());
			card.setCommentList(commentViewList);
			
			// !!!반드시 리스트에 넣는다.
			cardViewList.add(card);
		}
		// -> cardViewList에 꽂아넣는 행위가 필요로하다.
		
		return cardViewList;
	}
}
