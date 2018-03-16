$(document).ready(function(){
	var rootPath = "http://" + document.location.hostname + ":8080";
	
	//initialize Applicants & Job Dashboard
	loadAppJobDashboard(rootPath);
	
	//initialize all modal form
	//adding an applicant button click
	$(".add_applicant_btn").on("click", function(){
		$("form#new_applicant").attr("action", "new");
		$("#applicant-modal-dialog").modal("show");
		$("#new_applicant").find("input").each(function(){
			$(this).css("background-color", "");
			$(this).val("");
		});
		$("#new_applicant").find("textarea").val("");
		$("#new_applicant").find("#applicant-status").closest(".form-group").remove();
		$.ajax({
			url: rootPath + "/recruitment-tool-service/jobvacancy/find/open",
			method: "GET",
			dataType: "json",
			success: function(data){
				var result = data.data;
				$("#job-vacancy-select").find('option').remove();
				var option = "<option value>--------Select a Job Vacancy--------</option>";
				for(var key in result){
					option += "<option value='" + result[key].id + "'>" + result[key].title + "</option>";					
				}
				$("#job-vacancy-select").append(option);
			},
			error: function(err){
				console.log(err);
			}
		});
	});
	//adding a job button click
	$(".add_job_vacancy_btn").on("click", function(){
		$("form#new_job_vacancy").attr("action", "new");
		$("#jobvacancy-modal-dialog").modal("show");
		$("#jobvacancy-modal-dialog").find("#job-vacancy-status").closest(".form-group").remove();
		$("#new_job_vacancy").find("input").each(function(){
			$(this).css("background-color", "");
			$(this).val("");
		});
		$("#new_job_vacancy").find("textarea").val("");
	});
	
	//tab click
	$("#menu-tabs-select li a").on("click", function(e){		
		var tab = $(this).attr("tab_name");
		
		switch(tab){
			case "dashboard":
				loadAppJobDashboard(rootPath);
				break;
			case "applicants":
				loadAllApplicants(rootPath);
				break;
			case "jobvacancy":
				loadAllJobs(rootPath);
				break;
			case "maintenance":
				console.log("jobvacancy");
				break;
		}
	});	
	//dashboard click of list of applicants
	
	$('#home-app').click(function(){
		return;
		//$(this).slideUp();
		//$('#home-vac').slideUp();
		//$('#home-app-table').fadeIn();
		$('#home-app-table').dataTable( {						
	        "ajax": {
	        	url: rootPath +"/recruitment-tool-service/applicants/find/all",
	        	method: "GET",
	        	dataType: "json"
	        },
	        "columns": [
	            { "data": null, render: function(data, type, row){
	            	return data.firstName + " " + data.lastName;
	            } },
	            { "data": "email" },
	            { "data": "phone" },
	            { "data": "date" },
	            { "data" : null,
	              "defaultContent": "<span class='glyphicon glyphicon-edit edit-applicant hand-cursor' aria-hidden='true' title='Edit Applicant'></span> - " +
	              "<span class='glyphicon glyphicon-minus-sign delete-applicant hand-cursor' aria-hidden='true' title='Delete Applicant'></span>"
	            }
	        ],
	        "bDestroy": true,
	        "bFilter": false,
	        "fnCreatedRow": function( nRow, aData, iDataIndex ) {
	        	$(nRow).attr("applicant-id", aData.id);
	        }
	    } );

	});
	//dashboard click of Vacancies
	$('#home-vac').click(function(){
		return;
		$(this).slideUp();
		$('#home-app').slideUp();
		$('#home-vac-table').fadeIn();
		$('#home-vac-table').dataTable( {						
	        "ajax": {
	        	url: rootPath +"/recruitment-tool-service/jobvacancy/find/open",
	        	method: "GET",
	        	dataType: "json"
	        },
	        "columns": [
	            { "data": "title"},
	            { "data": "skill" },
	            { "data": "details" },
	            { "data": "date" }
	        ],
	        "bDestroy": true,
	        "bFilter": false,
	        "fnCreatedRow": function( nRow, aData, iDataIndex ) {
	        	$(nRow).attr("job-id", aData.id);
	        }
	    } );
	});	
	
	//new applicant
	$(".new_applicant_save").on("click", function(){
		
		var action = $("#new_applicant").attr("action");
		if(action == "new"){
			var isvalid = true;
			$("#new_applicant").find("input").each(function(){
				if($(this).val() == "" || $(this).val().length < 2){
					$(this).css("background-color", "red");
					isvalid = false;
				}
			});
			var form_data = $("#new_applicant").serialize();
			form_data += "&jobId=" + $("#job-vacancy-select option:selected").val();
			
			if(isvalid){
				$.ajax({
					url: rootPath +"/recruitment-tool-service/applicants/add",
					data: form_data,
					method: "POST",
					dataType: "json",
					success: function(data){				
						if(data.status == "200"){
							$(".applicants-tab").click();
							$("#applicant-modal-dialog").modal("hide");
						}
					},
					error: function(err){
						console.log(err);
					}
				})
			}
		}else{
			var appId = $("#new_applicant").attr("applicant-id");
			var appDate = $("#new_applicant").attr("applicant-date");
			
			var isvalid = true;
			$("#new_applicant").find("input").each(function(){
				if($(this).val() == "" || $(this).val().length < 2){
					$(this).css("background-color", "red");
					isvalid = false;
				}
			});
			var form_data = $("#new_applicant").serialize();
			var appStatus = $("#applicant-status option:selected").val();
			form_data += "&jobId=" + $("#job-vacancy-select option:selected").val() + "&id=" + appId + "&date=" + appDate + "&status=" + appStatus;
			
			if(isvalid){
				$.ajax({
					url: rootPath +"/recruitment-tool-service/applicants/update/?" + form_data,
					//data: data,
					method: "PUT",
					dataType: "json",
					success: function(data){				
						if(data.status == "200"){
							$(".applicants-tab").click();
							$("#applicant-modal-dialog").modal("hide");
						}
					},
					error: function(err){
						console.log(err);
					}
				})
			}
		}
	});
	
	//new job
	$(".new_job_vacancy_save").on("click", function(){
		var action = $("#new_job_vacancy").attr("action");
		if(action == "new"){
			var isvalid = true;
			$("#new_job_vacancy").find("input").each(function(){
				if($(this).val() == "" || $(this).val().length < 5){
					$(this).css("background-color", "red");
					isvalid = false;
				}
			});
			var form_data = $("#new_job_vacancy").serialize();
			
			if(isvalid){
				$.ajax({
					url: rootPath + "/recruitment-tool-service/jobvacancy/add",
					data: form_data,
					method: "POST",
					dataType: "json",
					success: function(data){				
						if(data.data == "success"){
							$(".jobvacancy-tab").click();
							$("#jobvacancy-modal-dialog").modal("hide");
						}
					},
					error: function(err){
						console.log(err);
					}
				});
			}
		}else{
			var isvalid = true;
			$("#new_job_vacancy").find("input").each(function(){
				if($(this).val() == "" || $(this).val().length < 5){
					$(this).css("background-color", "red");
					isvalid = false;
				}
			});
			var form_data = $("#new_job_vacancy").serialize();
			var jobid = $("#new_job_vacancy").attr("job-id");
			var jobdate = $("#new_job_vacancy").attr("job-date");
			var status = $("#job-vacancy-status option:selected").val();
			form_data += "&id=" + jobid + "&date=" + jobdate + "&status=" + status;
			if(isvalid){
				$.ajax({
					url: rootPath + "/recruitment-tool-service/jobvacancy/update/?" + form_data,
					//data: form_data,
					method: "PUT",
					dataType: "json",
					success: function(data){				
						if(data.message == "success"){
							$(".jobvacancy-tab").click();
							$("#jobvacancy-modal-dialog").modal("hide");
						}
					},
					error: function(err){
						console.log(err);
					}
				});
			}
		}
	});
	
	$("#new_applicant, #new_job_vacancy").find("input").keyup(function(){
		$(this).css("background-color", "");
	});	
	
});

//functions
function loadAllApplicants(url){
	$("#all-applicants-tab-table").dataTable( {						
        "ajax": {
        	url: url +"/recruitment-tool-service/applicants/find/all",
        	method: "GET",
        	dataType: "json"
        },
        "columns": [
            { "data": null, render: function(data, type, row){
            	return data.firstName + " " + data.lastName;
            } },
            { "data": "description" },
            { "data": "email" },
            { "data": "phone" },
            { "data": "date" },
            { "data": "status" },
            { "data" : null,
              "defaultContent":  "<span class='glyphicon glyphicon-edit edit-applicant hand-cursor' aria-hidden='true' title='Edit Applicant'></span>&nbsp;&nbsp;&nbsp;" +
              "<span class='glyphicon glyphicon-minus-sign delete-applicant hand-cursor' aria-hidden='true' title='Delete Applicant'></span>"
            }
        ],
        "bDestroy": true,
        "bFilter": false,
        "fnCreatedRow": function( nRow, aData, iDataIndex ) {
        	$(nRow).attr("applicant-id", aData.id);
        }
    } );
	//edit applicant
	$("#all-applicants-tab-table tbody").on("click", 'tr td span.edit-applicant', function(){
		var id = $(this).closest("tr").attr("applicant-id");
		$.ajax({
			url: url + "/recruitment-tool-service/applicants/find/" + id,				
			method: "GET",
			dataType: "json",
			success: function(data){
				console.log(data);
				$("#new_applicant").find("input").each(function(){
					$(this).css("background-color", "");
					$(this).val("");
				});
				$("#new_applicant").find("textarea").val("");
				
				if(!$.isEmptyObject(data.result)){
					var result = data.result;
					$("#applicant-modal-dialog").modal("show");
					$("form#new_applicant").find("#form-firstName").val(result.firstName);
					$("form#new_applicant").find("#form-lastName").val(result.lastName);
					$("form#new_applicant").find("#form-email").val(result.email);
					$("form#new_applicant").find("#form-description").val(result.description);
					$("form#new_applicant").find("#form-phone").val(result.phone);
					$("form#new_applicant").attr("applicant-id", result.id)
					$("form#new_applicant").attr("applicant-date", result.date);
					$("form#new_applicant").attr("action", "edit");
					$.ajax({
						url: url + "/recruitment-tool-service/jobvacancy/find/open",
						method: "GET",
						dataType: "json",
						success: function(data){
							var dataResult = data.data;
							$("#job-vacancy-select").find('option').remove();
							var option = "<option value>--------Select a Job Vacancy--------</option>";
							for(var key in dataResult){								
								if(result.jobId == dataResult[key].id){
									option += "<option value='" + dataResult[key].id + "' selected>" + dataResult[key].title + "</option>";
								}else{
									option += "<option value='" + dataResult[key].id + "'>" + dataResult[key].title + "</option>";
								}
							}
							$("#job-vacancy-select").append(option);
						},
						error: function(err){
							console.log(err);
						}
					});
					
					$.ajax({
						url: url + "/recruitment-tool-service/applicants/appstatus",
						method: "GET",
						dataType: "json",
						success: function(d){
							$("#applicant-status").find('option').remove();
							var option = "<option value>--------Select a Status--------</option>";
							for(var key in d){								
								if(result.status == key){
									option += "<option value='" + key + "' selected>" + d[key] + "</option>";
								}else{
									option += "<option value='" + key + "'>" + d[key] + "</option>";
								}
							}
							$("#applicant-status").append(option);
						},
						error: function(err){
							console.log(err);
						}
					});
				}
			},
			error: function(err){
				console.log(err);
			}
		});
	});
	
	//delete an applicant
	$("#all-applicants-tab-table tbody").on("click", 'tr td span.delete-applicant', function(){
		var id = $(this).closest("tr").attr("applicant-id");
		var con = confirm("Delete applicant?");
		if(con == true){
			$.ajax({
				url: url + "/recruitment-tool-service/applicants/delete/" + id,				
				method: "DELETE",
				dataType: "json",
				success: function(data){				
					$(".applicants-tab").click();
				},
				error: function(err){
					console.log(err);
				}
			});
		}
	});
}
//JObs
function loadAllJobs(url){
	$('#job-vacancy-all').dataTable( {						
        "ajax": {
        	url: url +"/recruitment-tool-service/jobvacancy/find/all",
        	method: "GET",
        	dataType: "json"
        },
        "columns": [
            { "data": "title"},
            { "data": "skill" },
            { "data": "details" },
            { "data" : "status"},
            { "data": "date" },
            { "data" : null,
              "defaultContent": "<span class='glyphicon glyphicon-edit edit-job-vacancy hand-cursor' aria-hidden='true' title='Edit Job'></span>&nbsp;&nbsp;&nbsp;" +
              "<span class='glyphicon glyphicon-minus-sign delete-job-vacancy hand-cursor' aria-hidden='true' title='Delete Job'></span>"
            }
        ],
        "bDestroy": true,
        "bFilter": false,
        "fnCreatedRow": function( nRow, aData, iDataIndex ) {
        	$(nRow).attr("job-id", aData.id);
        }
    } );
	
	//edit job vacancy
	$("#job-vacancy-all tbody").on("click", 'tr td span.edit-job-vacancy', function(){
		var id = $(this).closest("tr").attr("job-id");
		$.ajax({
			url: url + "/recruitment-tool-service/jobvacancy/find/" + id,				
			method: "GET",
			dataType: "json",
			success: function(data){				
				$("#new_job_vacancy").find("input").each(function(){
					$(this).css("background-color", "");
					$(this).val("");
				});
				$("#new_job_vacancy").find("textarea").val("");
				
				if(!$.isEmptyObject(data.result)){
					var result = data.result;
					$("#jobvacancy-modal-dialog").modal("show");
					$("form#new_job_vacancy").find("#job-title").val(result.title);
					$("form#new_job_vacancy").find("#job-skill").val(result.skill);
					$("form#new_job_vacancy").find("#job-details").val(result.details);
					$("form#new_job_vacancy").attr("job-id", result.id)
					$("form#new_job_vacancy").attr("job-date", result.date);
					$("form#new_job_vacancy").attr("action", "edit");
					$.ajax({
						url: url + "/recruitment-tool-service/jobvacancy/jobstatus/find/all",
						method: "GET",
						dataType: "json",
						success: function(data){
							$("#jobvacancy-modal-dialog").find("#job-vacancy-status").closest(".form-group").remove();
							var dataResult = data.data;
							var select = '<div class="form-group"><label for="job-vacancy-status">Job Status:</label>' +
								  '<select class="form-control" id="job-vacancy-status">';
							var option = "<option value>--------Select a Job Status--------</option>";
							
							for(var key in dataResult){
								var tmp = dataResult[key].name;
								if(result.status == dataResult[key].name){									
									option += "<option value='" + dataResult[key].name + "' selected>" + tmp.substring(0, 1).toUpperCase() +  tmp.substring(1)+ "</option>";
								}else{
									option += "<option value='" + dataResult[key].name + "'>"+ tmp.substring(0, 1).toUpperCase() +  tmp.substring(1)+ "</option>";
								}
							}
							select += option;
							select += "</select></div>";
							$("#jobvacancy-modal-dialog").find(".modal-body").append(select);
							
						},
						error: function(err){
							console.log(err);
						}
					});				
					
				}
			},
			error: function(err){
				console.log(err);
			}
		});
	});
	
	//delete an applicant
	$("#job-vacancy-all tbody").on("click", 'tr td span.delete-job-vacancy', function(){
		var id = $(this).closest("tr").attr("job-id");
		var con = confirm("Delete Job?");
		if(con == true){
			$.ajax({
				url: url + "/recruitment-tool-service/jobvacancy/delete/" + id,				
				method: "DELETE",
				dataType: "json",
				success: function(data){				
					$(".jobvacancy-tab").click();
				},
				error: function(err){
					console.log(err);
				}
			});
		}
	});
}

//initialize dashboard
function loadAppJobDashboard(url){
	
	$('#home-app-table').hide();
	$('#home-vac-table').hide();
	//get # of applicants
	$.ajax({
		url: url +"/recruitment-tool-service/applicants/countall",
		method: "GET",
		dataType: "json",
		success: function(data){
			if(!$.isEmptyObject(data)){
				if(data.status == 200){
					$(".no_applicants").html(data.count);
					
				}else{
					
				}
			}
		},
		error: function(err){
			console.log(err);
		}
	});
	
	//get # of jobs
	$.ajax({
		url: url +"/recruitment-tool-service/jobvacancy/countall",
		method: "GET",
		dataType: "json",
		success: function(data){
			if(!$.isEmptyObject(data)){
				if(data.status == 200){
					$(".no_vacancies").html(data.count);
				}else{
					
				}
			}
		},
		error: function(err){
			console.log(err);
		}
	});
}