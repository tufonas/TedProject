package com.ted.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the profession database table.
 * 
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="profession")
@NamedQueries({
				@NamedQuery(name="Profession.findAll", query="SELECT p FROM Profession p"),
				@NamedQuery(name="Profession.FindProfessionbyId", query="SELECT p FROM Profession p WHERE p.professionId=:requested_id"),
				@NamedQuery(name="Profession.updatePersonalData", query="UPDATE Profession p "
						+ "SET p.achievements=:requested_achievements,p.business_name=:requested_business_name,p.duties=:requested_duties,p.position=:requested_position,p.tomeas=:requested_tomeas WHERE p.user=:requested_user")
})
public class Profession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="profession_id")
	private int professionId;

	private String position;
	
	private String position_visibility;
	
	private String tomeas;
	
	private String tomeas_visibility;
	
	private String business_name;
	
	private String business_name_visibility;
	
	private String duties;
	
	private String duties_visibility;
	
	private String achievements;
	
	private String achievements_visibility;
	
	@ManyToOne 
	@XmlTransient 
	@JsonIgnore
	private User user;
	
	@ManyToOne
	@JoinTable
	(name="user_has_worked", 
	joinColumns={
			@JoinColumn(name="profession_id")
			}
	, inverseJoinColumns={
	@JoinColumn(name="experience_id" ,referencedColumnName="experience_id"),@JoinColumn(name="user_id",referencedColumnName="user_id") 
	})
	@XmlTransient    
	@JsonIgnore
	private Experience experience;
	
	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}


	public Profession() {
	}
	
	public String getAchievements() {
		return achievements;
	}

	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}

	public String getBusiness_name() {
		return business_name;
	}

	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}

	public String getDuties() {
		return duties;
	}

	public void setDuties(String duties) {
		this.duties = duties;
	}
	
	public String getPosition_visibility() {
		return position_visibility;
	}

	public void setPosition_visibility(String position_visibility) {
		this.position_visibility = position_visibility;
	}

	public String getAchievements_visibility() {
		return achievements_visibility;
	}

	public void setAchievements_visibility(String achievements_visibility) {
		this.achievements_visibility = achievements_visibility;
	}

	public String getBusiness_name_visibility() {
		return business_name_visibility;
	}

	public void setBusiness_name_visibility(String business_name_visibility) {
		this.business_name_visibility = business_name_visibility;
	}

	public String getDuties_visibility() {
		return duties_visibility;
	}

	public void setDuties_visibility(String duties_visibility) {
		this.duties_visibility = duties_visibility;
	}

	public String getTomeas_visibility() {
		return tomeas_visibility;
	}

	public void setTomeas_visibility(String tomeas_visibility) {
		this.tomeas_visibility = tomeas_visibility;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public int getProfessionId() {
		return this.professionId;
	}

	public void setProfessionId(int professionId) {
		this.professionId = professionId;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTomeas() {
		return this.tomeas;
	}

	public void setTomeas(String tomeas) {
		this.tomeas = tomeas;
	}

}