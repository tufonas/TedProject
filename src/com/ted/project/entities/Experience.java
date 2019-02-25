package com.ted.project.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the experience database table.
 * 
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="experience")
@NamedQueries({
				@NamedQuery(name="Experience.findAll", query="SELECT e FROM Experience e"),
				@NamedQuery(name="Experience.updatePersonalData", query="UPDATE Experience e SET e.education=:requested_education,e.skills=:requested_skills WHERE e.user=:requested_user")
})
public class Experience implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="experience_id")
	private int experienceId;

	private String education;
	
	private String education_visibility;

	private String skills;
	
	private String skills_visibility;
	@OneToOne
	@XmlTransient
	@JsonIgnore
	private User user;
	
	@OneToMany(mappedBy="experience")  
	private List<Profession> experience_jobs;  
	
	public Experience() {
		user = new User(); 
		experience_jobs = new ArrayList<Profession>();
	}

	public User getUser() {
		return user;
	}

	public String getEducation_visibility() {
		return education_visibility;
	}

	public void setEducation_visibility(String education_visibility) {
		this.education_visibility = education_visibility;
	}

	public String getSkills_visibility() {
		return skills_visibility;
	}

	public void setSkills_visibility(String skills_visibility) {
		this.skills_visibility = skills_visibility;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Profession> getExperience_jobs() {
		return experience_jobs;
	}

	public void setExperience_jobs(List<Profession> experience_jobs) {
		this.experience_jobs = experience_jobs;
	}

	public int getExperienceId() {
		return this.experienceId;
	}

	public void setExperienceId(int experienceId) {
		this.experienceId = experienceId;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSkills() {
		return this.skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

}