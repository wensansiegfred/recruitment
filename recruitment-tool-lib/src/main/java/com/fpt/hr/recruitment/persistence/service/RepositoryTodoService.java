package com.fpt.hr.recruitment.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.hr.recruitment.persistence.dao.TodoRepository;
import com.fpt.hr.recruitment.persistence.model.Todo;

@Service
public class RepositoryTodoService implements TodoService{
	
	private TodoRepository repository;

    @Autowired
    public RepositoryTodoService(TodoRepository repository) {
        this.repository = repository;
    }
    
	@SuppressWarnings("unchecked")
	public void add(Todo added){
		repository.insert(added);		
	}
	 
	public Todo findById(String id){		
        return repository.findOne(id);
	}
	 
}
