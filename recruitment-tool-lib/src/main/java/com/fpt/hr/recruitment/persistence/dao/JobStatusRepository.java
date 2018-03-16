package com.fpt.hr.recruitment.persistence.dao;

import java.util.List;

import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import com.fpt.hr.recruitment.persistence.model.JobStatus;

@Repository
public interface JobStatusRepository {
	
	public JobStatus findByName(String status);
	
	public void createStatus(JobStatus status);
	
	public List<JobStatus> getAll();
}
