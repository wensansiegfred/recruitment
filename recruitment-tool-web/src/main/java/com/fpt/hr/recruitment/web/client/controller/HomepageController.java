package com.fpt.hr.recruitment.web.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomepageController {
	
	@RequestMapping(value ="/homepage", method = RequestMethod.GET)
	public String loadHomepage(){
		return "homepage";
	}
}
