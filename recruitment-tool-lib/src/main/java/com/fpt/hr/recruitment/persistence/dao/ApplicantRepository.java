package com.fpt.hr.recruitment.persistence.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.fpt.hr.recruitment.persistence.model.Applicant;

@Repository
public interface ApplicantRepository {
	
    public void add(Applicant applicant);
 
    public Applicant readById(String id);
 
    public void update(Applicant applicant);
 
    public int deleteById(String id);
   
    public long countAllApplicant();
   
    public List<Applicant> getAllApplicant();
    
    public List<Applicant> getAllNewApplicant();
    
    public long countAllNewApplicant();
    
    public List<Applicant> findByName(String name);
}
