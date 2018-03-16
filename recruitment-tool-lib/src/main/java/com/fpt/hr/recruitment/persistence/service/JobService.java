package com.fpt.hr.recruitment.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fpt.hr.recruitment.persistence.dao.JobRepository;
import com.fpt.hr.recruitment.persistence.model.Job;
import com.fpt.hr.recruitment.persistence.model.JobStatus;
import com.mongodb.WriteResult;

@Service
@Transactional
public class JobService implements JobRepository{
	
	@Autowired
	MongoTemplate mongoTemplate;
	
    private static final String JOB_COLLECTION = "Job";//this matches your table
    private static final String JOBSTATUS_COLLECTION = "JobStatus";//this matches your table
    
    public void add(Job job) {
    	mongoTemplate.insert(job, JOB_COLLECTION);
    }
 
    public Job readById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Job.class, JOB_COLLECTION);
    }
 
    public void update(Job job) {
    	mongoTemplate.save(job, JOB_COLLECTION);
    }
 
    public int deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        WriteResult result = mongoTemplate.remove(query, Job.class, JOB_COLLECTION);
        return result.getN();
    }
   
    public long countAllJob(){
    	return mongoTemplate.count(null, Job.class, JOB_COLLECTION);
    }
   
    public List<Job> getAllJob(){
    	return mongoTemplate.findAll(Job.class, JOB_COLLECTION);
    }
    
    public List<Job> getAllNewJob(){
    	Query queryStatus = new Query(Criteria.where("name").is("open"));
    	JobStatus jobStatus = mongoTemplate.findOne(queryStatus, JobStatus.class, JOBSTATUS_COLLECTION);
    	Query query = new Query(Criteria.where("status").is(jobStatus.getName()));
    	return mongoTemplate.find(query, Job.class, JOB_COLLECTION);    	
    }
}
