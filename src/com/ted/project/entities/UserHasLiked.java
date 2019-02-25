package com.ted.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import java.sql.Timestamp;


/**
 * The persistent class for the user_has_liked database table.
 * 
 */
@Entity
@Table(name="user_has_liked")
@NamedQueries({
	@NamedQuery(name="UserHasLiked.findAllLikes", query="SELECT u FROM UserHasLiked u"),
	@NamedQuery(name="UserHasLiked.findLikeFromUserAndArticle", query="SELECT u FROM UserHasLiked u WHERE u.article=:requested_article AND u.user=:requested_user"),
	@NamedQuery(name="UserHasLiked.findLikesFromArticles", query="SELECT u FROM UserHasLiked u WHERE u.article IN :requested_articles  ORDER BY u.likedTime DESC "),
})													
public class UserHasLiked implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="like_id")
	private int likeId;


	@Column(name="liked_time")
	@XmlTransient
	private Timestamp likedTime;

	//bi-directional many-to-one association to Article
	@ManyToOne
	@JoinColumn(name="article_Id")
	@XmlTransient
	private Article article;

	//bi-directional many-to-one association to User
	@ManyToOne
	@XmlTransient
	private User user;

	public UserHasLiked() {
	}

	public int getLikeId() {
		return this.likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public Timestamp getLikedTime() {
		return this.likedTime;
	}

	public void setLikedTime(Timestamp likedTime) {
		this.likedTime = likedTime;
	}

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}