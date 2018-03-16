package com.fpt.hr.recruitment.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fpt.hr.recruitment.persistence.dao.JobStatusRepository;
import com.fpt.hr.recruitment.persistence.model.JobStatus;

@Service
@Transactional
public class JobStatusService implements JobStatusRepository{
	
	@Autowired
	MongoTemplate mongoTemplate;
	
    private static final String JOBSTATUS_COLLECTION = "JobStatus";
    
	public JobStatus findByName(String status){
		Query query = new Query(Criteria.where("name").is(status));
    	return mongoTemplate.findOne(query, JobStatus.class, JOBSTATUS_COLLECTION);
	}
	
	public void createStatus(JobStatus status){
		mongoTemplate.insert(status, JOBSTATUS_COLLECTION);
	}
	
	public List<JobStatus> getAll(){
		return mongoTemplate.findAll(JobStatus.class, JOBSTATUS_COLLECTION);
	}
}
