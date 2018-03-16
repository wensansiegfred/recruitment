package com.fpt.hr.recruitment.persistence.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fpt.hr.recruitment.persistence.dao.UserRepository;
import com.fpt.hr.recruitment.persistence.model.User;

@Service
@Transactional
public class UserService implements UserRepository{
	
	@Autowired
	MongoTemplate mongoTemplate;
	
    private static final String USER_COLLECTION = "User";
    
	public User getUserByUsername(String username){
		Query query = new Query(Criteria.where("username").is(username));
		return mongoTemplate.findOne(query, User.class, USER_COLLECTION);
	}
	
	public List<User> getAllUsers(){
		return mongoTemplate.findAll(User.class, USER_COLLECTION);
	}
	
	public void addUser(User user){
		mongoTemplate.insert(user, USER_COLLECTION);
	}
}
