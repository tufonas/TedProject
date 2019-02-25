package com.ted.project.entities;

import java.util.ArrayList;
import java.util.List;

public class HomeArticle {

	public HomeArticle() {
		articles = new ArrayList<Article>();
		user = new User();
		likes = new ArrayList<UserHasLiked>();
		comments = new ArrayList<Comment>();
		friends = new ArrayList<User>();
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
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

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getActive_comm_id() {
		return active_comm_id;
	}

	public void setActive_comm_id(Integer active_comm_id) {
		this.active_comm_id = active_comm_id;
	}

	private List<Article> articles;
	
	private User user;
	
	private List<User> friends;
	
	private List<Comment> comments;
	
	private List<UserHasLiked> likes;
	
	private Integer active_comm_id;
}
