package com.ted.project.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="comment")
@NamedQueries({
		@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c"),
		@NamedQuery(name="Comment.findCommentsFromArticles", query="SELECT c FROM Comment c WHERE c.article IN :requested_articles ORDER BY c.publishTime DESC"),	
})
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="comment_id")
	private int commentId;

	private String text;
	
	@ManyToOne
	@JoinTable
	(name="user_has_commented", 
	joinColumns={
			@JoinColumn(name="comment_id")
			}
	, inverseJoinColumns={
	@JoinColumn(name="article_id" ,referencedColumnName="article_id"),@JoinColumn(name="user_id" ,referencedColumnName="user_id")
	})
	private Article article;
	
	@XmlTransient  
	@Column(name="publish_time") 
	private Timestamp publishTime;
	
	public Timestamp getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	@ManyToOne
	@XmlTransient 
	private User user;
	


	public Comment() {
		article = new Article();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getCommentId() {
		return this.commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

}