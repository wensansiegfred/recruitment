package com.fpt.hr.recruitment.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fpt.hr.recruitment.persistence.dao.ApplicantRepository;
import com.fpt.hr.recruitment.persistence.model.Applicant;
import com.mongodb.WriteResult;

@Service
@Transactional
public class ApplicantService implements ApplicantRepository{
	
	@Autowired
	MongoTemplate mongoTemplate;
	
    private static final String APPLICANT_COLLECTION = "Applicant";
    
    public void add(Applicant applicant) {
    	mongoTemplate.insert(applicant, APPLICANT_COLLECTION);
    }
 
    public Applicant readById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Applicant.class, APPLICANT_COLLECTION);
    }
 
    public void update(Applicant applicant) {
    	mongoTemplate.save(applicant, APPLICANT_COLLECTION);
    }
 
    public int deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        WriteResult result = mongoTemplate.remove(query, Applicant.class, APPLICANT_COLLECTION);
        return result.getN();
    }
   
    public long countAllApplicant(){
    	return mongoTemplate.count(null, Applicant.class, APPLICANT_COLLECTION);
    }
   
    public List<Applicant> getAllApplicant(){    	
    	return mongoTemplate.findAll(Applicant.class, APPLICANT_COLLECTION);
    }
    
    public List<Applicant> getAllNewApplicant(){
    	Query query = new Query(Criteria.where("status").is("new"));
    	return mongoTemplate.find(query, Applicant.class, APPLICANT_COLLECTION);
    }
    
    public long countAllNewApplicant(){
    	Query query = new Query(Criteria.where("status").is("new"));
    	return mongoTemplate.count(query, Applicant.class, APPLICANT_COLLECTION);
    }
    
    public List<Applicant> findByName(String name){
    	Query query = new Query(Criteria.where("firstName").is(name));
    	return mongoTemplate.find(query, Applicant.class, APPLICANT_COLLECTION);
    }
}
