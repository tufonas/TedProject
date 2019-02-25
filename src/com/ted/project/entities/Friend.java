package com.ted.project.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="friends")
public class Friend {

	private int id;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private String telephone;
	
	
	private String profession;
	
	private String foreas;
	
	public Friend() {
		// TODO Auto-generated constructor stub
	}
	
	public void setUserToFriend(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.email = user.getEmail();
		this.telephone = user.getTelephone();
		this.profession = user.getProfession();
		this.foreas = user.getForeas();
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
}
