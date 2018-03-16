package com.fpt.hr.recruitment.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fpt.hr.recruitment.persistence.model.Applicant;
import com.fpt.hr.recruitment.persistence.model.Job;

public class Utilities {
	
	public final static String getCurrentDate(){
		Date now = new Date();
	    SimpleDateFormat myDateFormat = new SimpleDateFormat("MM-dd-yyyy");
	    String currDate = myDateFormat.format(now);
	    return currDate;
	}
	
	public final static Map<String, Object> getApplicantReponse(String message, List<Applicant> list){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "200");
		map.put("message", message);
		if(list != null){			
			map.put("count", list.size());
			map.put("data", list);
		}else{
			map.put("count", 0);
			map.put("data", null);
		}		
		return map;
	}	
	
	public final static Map<String, Object> getJobReponse(String message, List<Job> list){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "200");
		map.put("message", message);
		if(list != null){			
			map.put("count", list.size());
			map.put("result", list);
		}else{
			map.put("count", 0);
			map.put("result", null);
		}		
		return map;
	}
	
	public final static Map<String, String> formatGenericReponse(String status, String message, Long count){
		Map<String, String> response = new HashMap<String, String>();
		response.put("status", status);
		response.put("message", message);
		if(count != null){
			response.put("count", count.toString());
		}else{
			response.put("count", "0");
		}
		return response;
	}
	
	public final static Map<String, Object> formatSingleApplicantReponse(Applicant applicant){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "200");
		map.put("message", "success");
		map.put("count", 1);
		map.put("result", applicant);
		
		return map;
	}	
	
	public final static Map<String, Object> formatSingleJobReponse(Job job){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "200");
		map.put("message", "success");
		map.put("count", 1);
		map.put("result", job);
		
		return map;
	}
}
