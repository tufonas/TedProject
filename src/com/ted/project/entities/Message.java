package com.ted.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Time;
import java.sql.Date;


/**
 * The persistent class for the messages database table.
 * 
 */
@Entity
@Table(name="messages")
@NamedQueries({
				@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m"),
				@NamedQuery(name="Message.findMessagesByConversation", query="SELECT m FROM Message m WHERE m.conversation=:requested_conversation ORDER BY  m.messageDate ASC,m.messageTime ASC ")
})
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="message_id")
	private int messageId;

	private String message;
 
	@XmlTransient
	@Column(name="message_time")
	private Time messageTime;
	
	@XmlTransient
	@Column(name="message_date") 
	private Date messageDate; 

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	//bi-directional many-to-one association to Conversation
	@ManyToOne
	@JsonIgnore
	@XmlTransient
	@JoinColumn(name="conversation_id")
	private Conversations conversation;

	//bi-directional many-to-one association to User
	@ManyToOne  
	private User user;

	public Message() {
	}

	public int getMessageId() {
		return this.messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Time getMessageTime() {
		return this.messageTime;
	}

	public void setMessageTime(Time messageTime) {
		this.messageTime = messageTime;
	}

	public Conversations getConversation() {
		return this.conversation;
	}

	public void setConversation(Conversations conversation) {
		this.conversation = conversation;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}