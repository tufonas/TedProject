package com.ted.project.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="user")
public class XmlClass {

	private int id;
	private String name;
	private String surname;
	private String email;
	private String telephone;   
	private String profession;
	private String foreas;
	
	private List<Article> articles_published ;
	
	private List<Comment> comments;
	
	private List<Friend> friends;
	
	@XmlTransient
	private List<UserHasLiked> articles_liked ;    
	
	private Experience experience;
	
	private List<Aggelia> aggelies_published;
	
	public XmlClass() {
		friends = new ArrayList<Friend>();
	}

	public void setUserToXmlclass(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.email = user.getEmail();
		this.telephone = user.getTelephone();
		this.profession = user.getProfession();
		this.foreas = user.getForeas();  
		this.articles_liked=user.getArticles_liked();  
		this.experience=user.getExperience();
		this.comments = user.getComments();
		this.articles_published = user.getArticles_published();
		this.aggelies_published = user.getAggelies_published();
	}

	public  List<Comment>  getComments() {
		return comments;
	}

	public void setComments( List<Comment>  comments) {
		this.comments = comments;
	}

	public List<Article> getArticles_published() {
		return articles_published;
	}

	public void setArticles_published(List<Article> articles_published) {
		this.articles_published = articles_published;
	}
	public List<Aggelia> getAggelies_published() {
		return aggelies_published;
	}

	public void setAggelies_published(List<Aggelia> aggelies_published) {
		this.aggelies_published = aggelies_published;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getForeas() {
		return foreas;
	}

	public void setForeas(String foreas) {
		this.foreas = foreas;
	}

	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}

	public List<UserHasLiked> getArticles_liked() {
		return articles_liked;
	}

	public void setArticles_liked(List<UserHasLiked> articles_liked) {
		this.articles_liked = articles_liked;
	}

	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}
}
