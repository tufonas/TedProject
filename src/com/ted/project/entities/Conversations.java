package com.ted.project.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import com.ted.project.entities.Message;


/**
 * The persistent class for the conversation database table.
 * 
 */
@Entity
@NamedQueries({   
				@NamedQuery(name="Conversations.findAll", query="SELECT c FROM Conversations c"),  
				@NamedQuery(name="Conversations.findConversationsById", query="SELECT c FROM Conversations c WHERE c.user1=:requested_id OR c.user2=:requested_id"),
				@NamedQuery(name="Conversations.findConversationsByUserFriendId", query="SELECT c FROM Conversations c WHERE (c.user1=:requested_user AND c.user2=:requested_friend) OR (c.user2=:requested_user AND c.user1=:requested_friend)"),
				@NamedQuery(name="Conversations.findLastConversationOfUser",query="SELECT m.conversation FROM Message m WHERE m.messageTime=(SELECT MAX(m.messageTime) FROM Message m WHERE m.messageDate=(SELECT MAX(m.messageDate) FROM Message m WHERE m.conversation=(SELECT c FROM Conversations c WHERE c.user1=:requested_user OR c.user2=:requested_user))) ")
}) 
public class Conversations implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="conversation_id")
	@JsonIgnore
	private int conversationId;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="conversation")
	private List<Message> messages;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id1")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	
	@JoinColumn(name="user_id2")
	private User user2;

	public Conversations() {
	}

	public int getConversationId() {
		return this.conversationId;
	}

	public void setConversationId(int conversationId) {
		this.conversationId = conversationId;
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setConversation(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setConversation(null);

		return message;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

}