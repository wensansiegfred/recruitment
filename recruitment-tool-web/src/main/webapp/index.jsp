<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true"%>
<%
    /*
    	-This is temporary while im still working on the user authentication-
    
    */
	String redirectURL = "homepage";
    response.sendRedirect(redirectURL);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
	<title>FPT | Login</title>
<script src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/datatable/media/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/scripts.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/datatable/media/css/jquery.dataTables.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/custom.css">
</head>
<body>
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <img src="${pageContext.servletContext.contextPath}/resources/images/fpt-logo.png" width="15%" height="15%" />
	    </div>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="E-mail" name="email" type="email" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="password" type="password" value="">
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <a href="index.html" class="btn btn-warning btn-lg btn-success btn-block">Login</a>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>