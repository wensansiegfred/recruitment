package com.fpt.hr.recruitment.persistence.dao;

import java.util.List;

import com.fpt.hr.recruitment.persistence.model.ApplicantStatus;

public interface ApplicantStatusRepository {
	
	public ApplicantStatus findByStatus(String status);
	
	public void createStatus(ApplicantStatus appStatus);
	
	public List<ApplicantStatus> getAll();
	
}
