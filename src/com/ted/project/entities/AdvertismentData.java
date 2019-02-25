package com.ted.project.entities;

import java.util.ArrayList;
import java.util.List;

public class AdvertismentData {

	public AdvertismentData() {
		user = new User();
		ads = new ArrayList<Aggelia>();
		applAds = new ArrayList<Aggelia>();
		applications = new ArrayList<Application>();
	}
	public List<Application> getApplications() {
		return applications;
	}
	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	public List<Aggelia> getApplAds() {
		return applAds;
	}
	public void setApplAds(List<Aggelia> applAds) {
		this.applAds = applAds;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Aggelia> getAds() {
		return ads;
	}

	public void setAds(List<Aggelia> ads) {
		this.ads = ads;
	}
	private User user;
	
	private List<Aggelia> ads ;

	private List<Application> applications;
	
	private List<Aggelia> applAds;
}
