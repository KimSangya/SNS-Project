package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {

	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, Object> postCreate(
			@RequestParam(value = "content", required = false) String content,
			@RequestParam("file") MultipartFile file,
			HttpSession session
			) {
		
		// 글쓴이 번호를 session에서 꺼낸다.
		Integer userId = (Integer)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("loginId");
		
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		if(userId == null) {
			result.put("code", "403");
		}
		
		// db insert
		postBO.addPost(userId, userLoginId, content, file);
				
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> postDelete(
			@RequestParam("postId") int postId,
			HttpSession session) {
		// 글쓴이 번호를 불러와야함. 그 사람의 번호와 글 번호가 일치하는지까지는 봐야하니..
		Integer userId = (Integer)session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		if(userId == null) {
			result.put("code", 403);
			result.put("error_message", "로그인을 해주세요");
		}
		// db insert
		postBO.deletePost(postId, userId);
		
		// 응답 값
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
}
