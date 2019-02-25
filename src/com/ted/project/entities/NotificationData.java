package com.ted.project.entities;

import java.util.List;
import java.util.Map;

public class NotificationData {

	public NotificationData() {
	}
	
	private List<User> friend_requests;
	
	private List<Comment> comments;
	
	private List<UserHasLiked> likes;
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getFriend_requests() {
		return friend_requests;
	}

	public void setFriend_requests(List<User> friend_requests) {
		this.friend_requests = friend_requests;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<UserHasLiked> getLikes() {
		return likes;
	}

	public void setLikes(List<UserHasLiked> likes) {
		this.likes = likes;
	}



}
