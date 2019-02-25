package com.ted.project.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the aggelia database table.
 * 
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="aggelia")
@NamedQueries({
	@NamedQuery(name="Aggelia.findAll", query="SELECT a FROM Aggelia a"),
	@NamedQuery(name="Aggelia.findAggeliesToPrint", query="SELECT a FROM Aggelia a WHERE a.user!=:requested_user AND a.user IN :requested_friends ORDER BY a.publishTime DESC"),
	//@NamedQuery(name="Aggelia.findAggeliesToPrint", query="SELECT a FROM Aggelia a,Application b WHERE a.user!=:requested_user AND a.user IN :requested_friends AND (a = b.aggelia AND b.pending='TRUE') ORDER BY a.publishTime DESC"),
	@NamedQuery(name="Aggelia.findAggeliaById", query="SELECT a FROM Aggelia a WHERE a.advertismentId=:requested_id"),
	@NamedQuery(name="Aggelia.findAdvertismentsByApplications", query="SELECT distinct a FROM Aggelia a,Application b WHERE a.user=:requested_user AND a = b.ad AND b.pending='TRUE' ORDER BY a.publishTime DESC"),   
	
})  
public class Aggelia implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="aggelia_id")
	private int advertismentId;

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	@XmlTransient
	@OneToMany(mappedBy="ad",cascade={CascadeType.ALL})
	private List<Application> applications ;
	
	@Column(name="necessary_skills")
	private String necessarySkills;
	
	public String getNecessarySkills() {
		return necessarySkills;
	}

	public void setNecessarySkills(String necessarySkills) {
		this.necessarySkills = necessarySkills;
	}

	private String text;
	
	private String title;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ManyToOne
	@XmlTransient
	private User user;
	
	@XmlTransient
	@Column(name="publish_time") 
	private Timestamp publishTime;
	
	public Timestamp getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	public Aggelia() {
		user = new User();
	}

	public int getAdvertismentId() {
		return this.advertismentId;
	}

	public void setAdvertismentId(int adId) {
		this.advertismentId = adId;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}