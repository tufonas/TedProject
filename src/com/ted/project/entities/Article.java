
package com.ted.project.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;


/**
 * The persistent class for the article database table.
 * 
 */

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="article")
@NamedQueries({
	@NamedQuery(name="Article.findAll", query="SELECT a FROM Article a"),
	@NamedQuery(name="Article.findlastid", query="SELECT MAX(a.articleId) FROM Article a"),  
	@NamedQuery(name="Article.findArticleById", query="SELECT a FROM Article a WHERE a.articleId=:requested_id"),
	@NamedQuery(name="Article.findArticlesOfUser", query="SELECT a FROM Article a WHERE a.user=:requested_user"),
	@NamedQuery(name="Article.findArticlesForHome", query="SELECT a FROM Article a WHERE  a.user IN :requested_friends  ORDER BY a.publishTime DESC"),
	@NamedQuery(name="Article.findArticlesForHome1", query="SELECT a.article FROM UserHasLiked a WHERE a.user IN :requested_friends AND a.article.user NOT IN :requested_friends AND a.article.user!=:requested_user ORDER BY a.article.publishTime DESC")
}) 
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="article_id")
	private int articleId;

	private String title;
	
	private String text;
	
	@OneToMany(mappedBy="article",cascade={CascadeType.ALL})
	@JsonIgnore 
	@XmlTransient
	private List<UserHasLiked> users_liked ;
	
	@OneToMany(mappedBy="article")
	@JsonIgnore
	@XmlTransient
	private List<Comment> article_comments;

	private String videos;
	
	private String sounds;
	
	private String images;
	
	@XmlTransient
	@Column(name="publish_time") 
	private Timestamp publishTime;
	
	@XmlTransient
	public Timestamp getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	public List<Comment> getArticle_comments() {
		return article_comments;
	}

	public void setArticle_comments(List<Comment> article_comments) {
		this.article_comments = article_comments;
	}

	public String getVideos() {
		return videos;
	}

	public void setVideos(String videos) {
		this.videos = videos;
	}

	public String getSounds() {
		return sounds;
	}

	public void setSounds(String sounds) {
		this.sounds = sounds;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	@ManyToOne
	@XmlTransient
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Article() {
	}
	
	public int getArticleId() {
		return this.articleId;
	}

	public List<UserHasLiked> getUsers_liked() {
		return users_liked;
	}

	public void setUsers_liked(List<UserHasLiked> users_liked) {
		this.users_liked = users_liked;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}