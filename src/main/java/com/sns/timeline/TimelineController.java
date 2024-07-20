package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.Comment;
import com.sns.follow.bo.FollowBO;
import com.sns.follow.domain.Follow;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;

import jakarta.servlet.http.HttpSession;

@Controller
public class TimelineController {

	@Autowired
	private PostBO postBO; // 글을 가져와라 하는 PostBO를 나타냄
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private FollowBO followBO;
	
	@GetMapping("/timeline/timeline-view")
	public String timelineView(Model model) {
		
		List<PostEntity> postList = postBO.getPostEntityList();
		List<Comment> commentList = commentBO.getCommentList();
		List<Follow> followList = followBO.getFollowList();
		
		// 내가 여기서 따로 팔로우 리스트를 가져오려면, 내가 가져온 인원이 누구인가에 대해서 리턴을 해줘야함.
		// 그 팔로우 리스트에서 내 값을 리턴을 해주고, 그 값을 where절로 생각을 해서, 넣어주는게 필요로함.
		// 그럼 리턴되는 값을 생각해서, 그 값을 넣어주기만 하면 되는 부분임.
		// 만약 나오는 값이 2,3절이 있고, followList.FromUser == post.userId값을 생각해서 만약 그 값이 있다면 팔로잉으로 표시를 하게 하면 되는것임.
		// 이 값을 가져오려면 Session이 필요함.
		
		model.addAttribute("postList", postList);
		model.addAttribute("commentList", commentList);
		model.addAttribute("followList", followList);
		
		return "timeline/timeline";
	}
}
