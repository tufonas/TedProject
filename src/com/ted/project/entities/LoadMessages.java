package com.ted.project.entities;

import java.util.ArrayList;
import java.util.List;

public class LoadMessages {

	public LoadMessages() {
		messages = new ArrayList<Message>();

	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}


	private List<User> user;
	private List<Message> messages;
}
