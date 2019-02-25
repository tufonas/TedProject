package com.ted.project.entities;

import java.util.ArrayList;
import java.util.List;

public class ProfileData {

	
	private User logged_user;
	
	private User profile_user;
	
	private String type_of_user;
	
	private List<User> friends;
	
	public ProfileData() {
		// TODO Auto-generated constructor stub
		logged_user = new User();
		profile_user = new User();
		friends = new ArrayList<User>();
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public String getType_of_user() {
		return type_of_user;
	}

	public void setType_of_user(String type_of_user) {
		this.type_of_user = type_of_user;
	}

	public User getLogged_user() {
		return logged_user;
	}

	public void setLogged_user(User logged_user) {
		this.logged_user = logged_user;
	}

	public User getProfile_user() {
		return profile_user;
	}

	public void setProfile_user(User profile_user) {
		this.profile_user = profile_user;
	}
	
	
}
