package com.fpt.hr.recruitment.persistence.service;

import com.fpt.hr.recruitment.persistence.model.Todo;

public interface TodoService {
	
	 public void add(Todo added);
	 
	 public Todo findById(String id);
}
