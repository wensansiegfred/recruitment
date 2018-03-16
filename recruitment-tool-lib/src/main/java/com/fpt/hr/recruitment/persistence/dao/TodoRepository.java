package com.fpt.hr.recruitment.persistence.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fpt.hr.recruitment.persistence.model.Todo;

public interface TodoRepository extends MongoRepository<Todo, String>{

}
