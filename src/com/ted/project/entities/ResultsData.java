package com.ted.project.entities;

import java.util.ArrayList;
import java.util.List;

public class ResultsData {

	public ResultsData() {
		usersByName = new ArrayList<User>();
		usersBySurname = new ArrayList<User>();
		usersByNameSurname = new ArrayList<User>();
		friend_requests = new ArrayList<User>();
		friends = new ArrayList<User>();
		user = new User();
	}
	
	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	private List<User> usersByName;
	
	private List<User> friend_requests;
	
	public List<User> getFriend_requests() {
		return friend_requests;
	}

	public void setFriend_requests(List<User> friend_requests) {
		this.friend_requests = friend_requests;
	}

	private List<User> friends;
	
	private List<User> usersBySurname;
	
	private List<User> usersByNameSurname;
	
	private User user;

	public List<User> getUsersByName() {
		return usersByName;
	}

	public void setUsersByName(List<User> usersByName) {
		this.usersByName = usersByName;
	}

	public List<User> getUsersBySurname() {
		return usersBySurname;
	}

	public void setUsersBySurname(List<User> usersBySurname) {
		this.usersBySurname = usersBySurname;
	}

	public List<User> getUsersByNameSurname() {
		return usersByNameSurname;
	}

	public void setUsersByNameSurname(List<User> usersByNameSurname) {
		this.usersByNameSurname = usersByNameSurname;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
