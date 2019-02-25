package com.ted.project.entities;

import java.io.Serializable;
import java.sql.Timestamp;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAccessType;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the users database table.
 * 
 */


@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name="User.findProfessionals", query="SELECT p FROM User p WHERE p.typeOfUser='epaggelmatias'"),
	@NamedQuery(name="User.findAll", query="SELECT p FROM User p WHERE p.typeOfUser='epaggelmatias'"),
	@NamedQuery(name="User.findEmail_Password", query="SELECT p FROM User p WHERE p.email = :requested_email AND p.password = :requested_password"),
	@NamedQuery(name="User.findUserbyEmails", query="SELECT p FROM User p WHERE p.email IN :requested_id"),
	@NamedQuery(name="User.findUserByEmail", query="SELECT p FROM User p WHERE p.email = :requested_email"),
	@NamedQuery(name="User.checkVerify", query="SELECT p.verify FROM User p WHERE p.email = :requested_email"),
	@NamedQuery(name="User.updateVerify", query="UPDATE User p SET p.verify=:requested_verify WHERE p.email=:requested_email"),
	@NamedQuery(name="User.FindUserbyId", query="SELECT p FROM User p  WHERE p.id=:requested_id "),
	@NamedQuery(name="User.FindUserbyName", query="SELECT p FROM User p  WHERE p.name=:requested_name AND p.id!=:current_user "),
	@NamedQuery(name="User.FindUserbySurname", query="SELECT p FROM User p  WHERE p.surname=:requested_surname AND p.id!=:current_user "),
	@NamedQuery(name="User.FindUserbyNameSurname", query="SELECT p FROM User p  WHERE p.surname=:requested_surname AND p.name=:requested_name AND p.id !=:current_user"),
	@NamedQuery(name="User.deleteFriendRequest", query="DELETE FROM User p WHERE p=:requested_user and p.friendRequest=:requested_friend"),
	@NamedQuery(name="User.findMyFriendRequest", query="SELECT p FROM User p WHERE p.friendRequest=:requested_user"),
	@NamedQuery(name="User.findLastUserId", query="SELECT MAX(p.id) FROM User p"), 
	
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@ManyToMany
	@JoinTable(name="friendship",  
	joinColumns={	
					@JoinColumn(name="user_id1")
		}
		, inverseJoinColumns={
					@JoinColumn(name="user_id2")
		} 
	)
	@JsonIgnore
	private List<User> friend1;
	
	@ManyToMany
	@JoinTable(name="friendship",  
	joinColumns={	
					@JoinColumn(name="user_id2")
		}
		, inverseJoinColumns={
					@JoinColumn(name="user_id1")
		} 
	)
	@JsonIgnore 
	private List<User> friend2;
	
	private String image;
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@ManyToMany
	@JoinTable(name="friend_request",
	joinColumns={	
					@JoinColumn(name="user_id")
		}
		, inverseJoinColumns={
					@JoinColumn(name="friend_request_id")
		} 
	)
	@JsonIgnore 
	private List<User> friendRequest;
	
	public List<User> getFriendRequest() {
		return friendRequest;
	}

	public void setFriendRequest(List<User> friendRequest) {
		this.friendRequest = friendRequest;
	}
	@OneToMany(mappedBy="user1",cascade={CascadeType.ALL})
	@JsonIgnore
	@XmlTransient
	private List<Conversations> conversation1;
		
	@OneToMany(mappedBy="user2",cascade={CascadeType.ALL})
	@JsonIgnore
	@XmlTransient
	private List<Conversations> conversation2;
	
	@OneToMany(mappedBy="user",cascade={CascadeType.ALL})
	@JsonIgnore 
	@XmlTransient
	private List<Message> messages;
	
	public List<Conversations> getConversation1() {
		return conversation1;
	}

	public void setConversation1(List<Conversations> conversation1) {
		this.conversation1 = conversation1;
	}

	public List<Conversations> getConversation2() {
		return conversation2;
	}

	public void setConversation2(List<Conversations> conversation2) {
		this.conversation2 = conversation2;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	@OneToMany(mappedBy="user",cascade={CascadeType.ALL})
	@JsonIgnore 
	private List<UserHasLiked> articles_liked ;
	
	@JsonIgnore
	@OneToMany(mappedBy="user",cascade={CascadeType.ALL})
	private List<Application> applications ;
	
	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	@OneToMany(mappedBy="user",cascade={CascadeType.ALL})
	@JsonIgnore 
	private List<Comment> comments;
	
	@OneToMany(mappedBy="user",cascade={CascadeType.ALL})
	private List<Profession> jobs;
	
	@OneToMany(mappedBy="user" ,cascade={CascadeType.ALL})
	@JsonIgnore 
	private List<Article> articles_published; 
	
	@OneToOne(mappedBy="user", cascade={CascadeType.ALL})  
	private Experience experience;
	
	@OneToMany(mappedBy="user" ,cascade={CascadeType.ALL})
	@JsonIgnore 
	private List<Aggelia> aggelies_published;
	
	private String email;

	private String name;

	private String verify;
	
	private String foreas;

	private String password;

	private String profession;

	private String surname;

	private String telephone;

	@Column(name="type_of_user")
	private String typeOfUser;

	public User() {
		friend1 = new ArrayList<User>();
		friend2 = new ArrayList<User>();
	}
	
	public List<User> getFriend1() {
		return friend1;
	}

	public void setFriend1(List<User> friend1) {
		this.friend1 = friend1;
	}

	public List<User> getFriend2() {
		return friend2;
	}

	public void setFriend2(List<User> friend2) {
		this.friend2 = friend2;
	}

	public List<Profession> getJobs() {
		return jobs;
	}

	public void addUserToFriend(User user2)
	{
		this.getFriend2().add(user2);
		user2.getFriend2().add(this);
	}
	public void setJobs(List<Profession> jobs) {
		this.jobs = jobs;
	}
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Article> getArticles_published() {
		return articles_published;
	}

	public void setArticles_published(List<Article> articles_published) {
		this.articles_published = articles_published;
	}

	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}
	
	public List<Aggelia> getAggelies_published() {
		return aggelies_published;
	}

	public List<UserHasLiked> getArticles_liked() {
		return articles_liked;
	}

	public void setArticles_liked(List<UserHasLiked> articles_liked) {
		this.articles_liked = articles_liked;
	}

	public void setAggelies_published(List<Aggelia> aggelies_published) {
		this.aggelies_published = aggelies_published;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTypeOfUser() {
		return this.typeOfUser;
	}

	public void setTypeOfUser(String typeOfUser) {
		this.typeOfUser = typeOfUser;
	}

	public String getForeas() {
		return foreas;
	}

	public void setForeas(String foreas) {
		this.foreas = foreas;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}
	public void addLikeToArticle(Article article,UserHasLiked like,Timestamp time)
	{
		article.getUsers_liked().add(like);
		this.getArticles_liked().add(like);
		like.setUser(this);
		like.setArticle(article);
		like.setLikedTime(time);
	}
	public void removeLikeFromArticle(Article article,UserHasLiked like)
	{
		article.getUsers_liked().remove(like);
		this.getArticles_liked().remove(like);
	}

}