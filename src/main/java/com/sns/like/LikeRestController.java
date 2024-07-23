package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeRestController {

	@Autowired
	private LikeBO likeBO;
	
	
	// GET : /like?postId=13 원래하던 부분은 이런식 					@RequestParam("postId")
	// GET : /like/13 다만 이런식으로 설정을 해서 해볼 것임.				@PathVariable("") // 패스에 변하는 변수값을 껴서 넣겠다라는 의미.
	
	@RequestMapping("/like/{postId}")
	public Map<String, Object> likeToggle(
			@PathVariable(name = "postId") int postId,
			HttpSession session) { // 이런식으로 매핑을 하는것이다.
		
		// 로그인 여부 : 로그인이 안되어있을때 확인
		Integer userId = (Integer)session.getAttribute("userId"); // 필요한것만 확인
		Map<String, Object> result = new HashMap<>();
				
		if(userId == null) { // 만약 로그인이 안됐다면, 이런식으로 리턴을 해주게 된다.
			result.put("code", 403);
			result.put("error_message", "로그인을 해주세요");
			return result;
		}		
				
		// likeToggleBO에게 요청을 한다. 응답값을 따로 받아오진 않는다. void로 하게 된다.
		likeBO.likeToggle(postId, userId);
		
		// 성공 응답
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
}
