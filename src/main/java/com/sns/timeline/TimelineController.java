package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;

import jakarta.servlet.http.HttpSession;

@Controller
public class TimelineController {

	@Autowired
	private PostBO postBO; // 글을 가져와라 하는 PostBO를 나타냄
	
	@GetMapping("/timeline/timeline-view")
	public String timelineView(Model model) {
		List<PostEntity> postList = postBO.getPostEntityList();
		
		model.addAttribute("postList", postList);
		
		return "timeline/timeline";
	}
}
