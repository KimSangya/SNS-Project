package com.sns.follow;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.follow.bo.FollowBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/follow")
@RestController
public class FollowRestController {

	@Autowired
	private FollowBO followBO;
	
	@PostMapping("/following")
	public Map<String, Object> following(
			@RequestParam("userId") Integer userId,
			@RequestParam("postUserId") int postUserId,
			HttpSession session) {
		
		// 로그인 여부 확인
		userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		
		if(userId == null) {
			result.put("code", 403);
			result.put("error_message", "로그인을 해주세요");
			return result;
		}

		// db 설정
		followBO.addFollow(userId, postUserId);
		
		// 응답값
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
}
