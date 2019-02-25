package com.ted.project.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the applications database table.
 * 
 */
@Entity
@Table(name="applications")
@NamedQueries({
	@NamedQuery(name="Application.findAll", query="SELECT a FROM Application a"),
	@NamedQuery(name="Application.findApplicationByAdAndUser", query="SELECT a FROM Application a WHERE a.user=:requested_user and a.ad=:requested_ad"),
})
public class Application implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="application_id") 
	private int applicationId;
	
	@Column(name="publish_time")
	private Timestamp publishTime;

	@Column(name="pending")  
	private String pending;  
	
	public String getPending() {
		return pending;
	}

	public void setPending(String pending) {
		this.pending = pending;
	}

	//bi-directional many-to-one association to Aggelia
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="advertisment_id")
	private Aggelia ad;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Application() {
	}

	public Timestamp getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	public Aggelia getAd() {
		return this.ad;
	}

	public void setAd(Aggelia aggelia) {
		this.ad = aggelia;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}