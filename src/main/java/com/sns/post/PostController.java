package com.sns.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class PostController {
	
	@GetMapping("/post-list-view")
	public String postList() {
		return "post/postList";
	}
}
