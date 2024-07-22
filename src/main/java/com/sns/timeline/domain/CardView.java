package com.sns.timeline.domain;

import java.util.List;

import com.sns.comment.domain.CommentView;
import com.sns.post.entity.PostEntity;
import com.sns.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

// view 용 객체
// 글 1개와 매핑됨
@ToString
@Data
public class CardView { // card라는 명칭을 쓰는 이유는 타임라인에 한개 한개 마다 카드라고 말하기 때문에 cardview라고 적기 때문이다.
						// 만약 상품을 하나하나 한다고 하면 productview가 될 것이다.
						// 복사하는 두가지,
						// 아예 복사를 해서 가져오던지, 또는 아예 엔티티 자체를 가져오던지
	// 글 1개
	private PostEntity post; // 글 하나
	
	// 글쓴이 정보
	private UserEntity user; // 글쓴이 이름 가져오기
	
	// 댓글 N개
	private List<CommentView> commentList;
	
	// 좋아요 N개
	
	
	// 좋아요를 누른지 여부
	
	
	
	
}
