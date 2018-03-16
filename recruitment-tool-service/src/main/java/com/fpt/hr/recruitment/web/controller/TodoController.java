package com.fpt.hr.recruitment.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.hr.recruitment.persistence.model.Todo;
import com.fpt.hr.recruitment.persistence.service.TodoService;

@RestController
@RequestMapping(value = "/todo")
public class TodoController {
	private TodoService service;
	
	@Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }
	 
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Todo getTodoById(@PathVariable("id") final String id){
		return service.findById(id);
	}
}
