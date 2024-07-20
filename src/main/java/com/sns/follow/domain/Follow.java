package com.sns.follow.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Follow {

	private int id;
	private int fromUser;
	private int toUser;
	private LocalDateTime createdAt;
}
