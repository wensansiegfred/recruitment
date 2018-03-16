package com.fpt.hr.recruitment.persistence.dto;

import javax.validation.constraints.NotNull;

public class JobDto {
	
	@NotNull
	private String title;
	
	@NotNull
	private String skill;		
	
	@NotNull
	private String details;
	
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
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}
