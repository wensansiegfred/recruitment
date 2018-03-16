package com.fpt.hr.recruitment.persistence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Job {
	
	@Id
	private String id;
	
	private String title;
	private String skill;
	private String status;
	private String details;
	private String date;
	
	public Job(String id, String title, String skill, String status, String details, String date){
		this.id = id;
		this.title = title;
		this.skill = skill;
		this.status = status;
		this.details = details;
		this.date = date;
	}
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
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public void setOthers(String details) {
		this.details = details;
	}	
}
