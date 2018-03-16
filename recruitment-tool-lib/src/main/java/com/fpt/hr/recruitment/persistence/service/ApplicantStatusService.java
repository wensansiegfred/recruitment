package com.fpt.hr.recruitment.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.fpt.hr.recruitment.persistence.dao.ApplicantStatusRepository;
import com.fpt.hr.recruitment.persistence.model.ApplicantStatus;

@Service
public class ApplicantStatusService implements ApplicantStatusRepository{
	
	@Autowired
	MongoTemplate mongoTemplate;
	
    private static final String APPLICANTSTATUS_COLLECTION = "ApplicantStatus";
    
	public ApplicantStatus findByStatus(String status){
		Query query = new Query(Criteria.where("name").is(status));
    	return mongoTemplate.findOne(query, ApplicantStatus.class, APPLICANTSTATUS_COLLECTION);
	}
	
	public void createStatus(ApplicantStatus appStatus){
		mongoTemplate.insert(appStatus, APPLICANTSTATUS_COLLECTION);
	}
	
	public List<ApplicantStatus> getAll(){
		return mongoTemplate.findAll(ApplicantStatus.class, APPLICANTSTATUS_COLLECTION);
	}
}
