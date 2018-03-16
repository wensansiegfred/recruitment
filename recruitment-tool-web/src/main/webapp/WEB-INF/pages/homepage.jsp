<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
	<title>Welcome :: FPT Recruitment Homepage</title>
<script src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/datatable/media/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/scripts.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/datatable/media/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/custom.css">
<style>
	.dataTables_length{
		display: none;
	}
	.hand-cursor{
		cursor: pointer;
	}
</style>
</head>
<body>
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <!-- <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false" /> -->
	      <ul class="nav navbar-nav navbar-left">
	        <li>
	          <img src="${pageContext.servletContext.contextPath}/resources/images/fpt-logo.png" width="15%" height="15%" />
	        </li>
	      </ul>
	      
	      <ul class="nav navbar-nav navbar-right">
	        <li>
	          <a href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Logout <span class="glyphicon glyphicon-log-out"></span></a>
	        </li>
	      </ul>
	    </div>
	  </div><!-- /.container-fluid -->
	</nav>
	<div class="container">
		<div class="row">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs" role="tablist" id="menu-tabs-select">
				<li role="presentation" class="active">
				  	<a href="#dashboard" aria-controls="home" class="dashboard-tab" tab_name="dashboard" role="tab" data-toggle="tab">Dashboard</a>
				</li>
				<li role="presentation">
				  	<a href="#applicants" aria-controls="profile" class="applicants-tab" tab_name="applicants" role="tab" data-toggle="tab">Applicants</a>
				</li>
				<li role="presentation">
				   	<a href="#jobvacancy" aria-controls="messages" class="jobvacancy-tab" tab_name="jobvacancy" role="tab" data-toggle="tab">Jobs</a>
				</li>
				<li role="presentation">
				   	<a href="#maintenance" aria-controls="settings" class="maintenance-tab" tab_name="maintenance" role="tab" data-toggle="tab">Maintenance</a>
				</li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="dashboard">
					<div class="page-header">
					  <h2>Dashboard</h2>
					</div>
					<div class="row">
						<div class="dash-notification">
							<div class="col-lg-4 align-center notif notification-f" id="home-app">
								<h3 class="notify no_applicants">0</h3>
								<p class="notify">New Applicants</p>
							</div>
							<div class="col-lg-4 align-center notif notification-p" id="home-vac">
								<h3 class="notify no_vacancies">10</h3>
								<p class="notify">Job Vacancies</p>
							</div>
						</div>
						<div class="dash-tables">
							<!-- List of New Applicants -->
							<div class="col-lg-12">
								<table id="home-app-table" class="table table-striped table-bordered" cellspacing="0" width="100%">
									<thead>
										<tr>
											<th>Name</th>
											<th>Email</th>
											<th>Phone</th>
											<th>Date</th>
										</tr>
									</thead>								
								</table>
							</div>
							<!-- List of Open Jobs -->
							<div class="col-lg-12">
								<table id="home-vac-table" class="table table-striped table-bordered" cellspacing="0" width="100%">
									<thead>
										<tr>
											<th>Title</th>
											<th>Skill(s)</th>
											<th>Details</th>
											<th>Date</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
						</div>
					</div>
				<div role="tabpanel" class="tab-pane" id="applicants">
					<div class="row">
						<div class="col-lg-12">
							<div class="page-header">
					  			<h2>Applicants</h2>
							</div>
							<table id="all-applicants-tab-table" class="table table-striped table-bordered" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>Name</th>
										<th>Short Description</th>
										<th>Email</th>
										<th>Phone</th>
										<th>Date</th>
										<th>Status</th>
										<th>&nbsp;</th>
									</tr>
								</thead>
							</table>
							<!-- Button trigger modal -->
							<button type="button" class="btn btn-warning btn-lg add_applicant_btn">
								Add Applicant
							</button>
						</div>
					</div>

					<!-- Modal -->
					<div class="modal fade" id="applicant-modal-dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					        <h4 class="modal-title" id="myModalLabel">Fill up applicant information</h4>
					      </div>
					      <form id="new_applicant">
						      <div class="modal-body new_applicant_modal">
						      	<label for="form-firstName">First Name:</label>
					       		<input type="text" class="form-control" name="firstName" id="form-firstName" placeholder="First Name" required/>
					       		<label for="form-lastName">Last Name:</label>
					       		<input type="text" class="form-control" name="lastName" id="form-lastName" placeholder="Last Name" required/>
					       		<label for="form-email">Email:</label>
					       		<input type="text" class="form-control" name="email" id="form-email" placeholder="E-mail Address" required/>
					       		<label for="form-phone">Phone:</label>
					       		<input type="text" class="form-control" name="phone" id="form-phone" placeholder="Phone number" required/>
					       		<label for="form-description">Description:</label>
					       		<textarea class="form-control" name="description" id="form-description" placeholder="Short Description" required></textarea>
					       		<!-- <input type="text" class="form-control" placeholder="Source" /> -->
					       		<div class="form-group">
								  <label for="job-vacancy-select">Match a Job Vacancy:</label>
								  <select class="form-control" id="job-vacancy-select">
								  	<option value="">--------Select a Job Vacancy--------</option> 
								  </select>
								</div>
								<div class="form-group">
								  <label for="applicant-status">Applicant Status:</label>
								  <select class="form-control" id="applicant-status">								    								    
								  </select>
								</div>					       		
							  </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-danger close_btn" data-dismiss="modal">Close</button>
						        <button type="button" class="btn btn-primary new_applicant_save">Save</button>
						      </div>
					      </form>
					    </div>
					  </div>
					</div>
			    </div>
			    <!--  Jobs -->
			    <div role="tabpanel" class="tab-pane" id="jobvacancy">
					<div class="row">
						<div class="col-lg-12">
							<div class="page-header">
					  			<h2>Job Vacancy</h2>
							</div>
							<table id="job-vacancy-all" class="table table-striped table-bordered" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>Title</th>
										<th>Skill(s)</th>
										<th>Details</th>
										<th>Status</th>
										<th>Date</th>
										<th>&nbsp;</th>
									</tr>
								</thead>								
							</table>
							<!-- Button trigger modal -->
							<button type="button" class="btn btn-warning btn-lg add_job_vacancy_btn">
							  Add Job Vacancy
							</button>
						</div>
					</div>
					<!-- Modal -->
					<div class="modal fade" id="jobvacancy-modal-dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<form id="new_job_vacancy">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						        <h4 class="modal-title" id="myModalLabel">Add Job Vacancy</h4>
						      </div>
						      <div class="modal-body">
						      	<label for="job-title">Job Title:</label>
				        		<input type="text" class="form-control" name="title" id="job-title" placeholder="Title" />
				        		<label for="job-skill">Skill(s):</label>
				        		<input type="text" class="form-control" name="skill" id="job-skill" placeholder="Skill" />
				        		<label for="job-details">Short Details:</label>
				        		<textarea class="form-control" name="details" id="job-details" placeholder="Details" required></textarea>
							  </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-danger new_job_vacancy_close" data-dismiss="modal">Close</button>
						        <button type="button" class="btn btn-primary new_job_vacancy_save">Save</button>
						      </div>
						    </div>
						  </div>
					  </form>
					</div>
			    </div>
			    <div role="tabpanel" class="tab-pane" id="maintenance">
			    	<div class="page-header">
					  <h2>Maintenance</h2>
					</div>
			    </div>
		  </div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>