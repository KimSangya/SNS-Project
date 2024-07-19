package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	
	@Autowired
	private CommentBO commentBO;
	
	@PostMapping("/create") // TODO postMapping으로 써야함
	public Map<String, Object> create(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpSession session) {
		
		// 로그인 여부 : 로그인이 안되어있을때 확인
		Integer userId = (Integer)session.getAttribute("userId"); // 필요한것만 확인
		Map<String, Object> result = new HashMap<>();
		
		if(userId == null) { // 만약 로그인이 안됐다면, 이런식으로 리턴을 해주게 된다.
			result.put("code", 403);
			result.put("error_message", "로그인을 해주세요");
			return result;
		}
		
		// db insert
		commentBO.addComment(postId, userId, content); // 확인하면서 천천히 봐라
		
		// 응답값
		result.put("code", 200);
		result.put("result", "성공");
		return result;
		
		//localhost:8080/comment/create?postId=?
	}
}
