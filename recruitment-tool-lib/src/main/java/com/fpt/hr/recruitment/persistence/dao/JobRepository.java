package com.fpt.hr.recruitment.persistence.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.fpt.hr.recruitment.persistence.model.Job;

@Repository
public interface JobRepository {
	
    public void add(Job job);
 
    public Job readById(String id);
 
    public void update(Job job);
 
    public int deleteById(String id);
   
    public long countAllJob();
   
    public List<Job> getAllJob();
    
    public List<Job> getAllNewJob();
}
