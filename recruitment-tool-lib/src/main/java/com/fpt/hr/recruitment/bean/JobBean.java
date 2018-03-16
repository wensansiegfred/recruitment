package com.fpt.hr.recruitment.bean;

public class JobBean {
	
	private String id;
	private String title;
	private String skill;
	private String status;
	private String details;
	private String date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}	
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}	
}
