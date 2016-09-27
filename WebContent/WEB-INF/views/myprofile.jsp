<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Edit my profile</title>
    

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container myprofile">

    <form:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading">My profile</h2>
        
        <div class="row">
        	<div class="col-sm-4">
				<spring:bind path="username">
		        	<span><b>username</b></span>
		            <div class="form-group ${status.error ? 'has-error' : ''}">
		                <form:input type="text" path="username" class="form-control" placeholder="Username"
		                            autofocus="true"></form:input>
		                <form:errors path="username"></form:errors>
		            </div>
		        </spring:bind>
		        
		        
		        <spring:bind path="useremail">
		        	<span><b>email</b></span>
		            <div class="form-group ${status.error ? 'has-error' : ''}">
		                <form:input type="text" path="useremail" class="form-control" placeholder="Email"></form:input>
		                <form:errors path="useremail"></form:errors>
		            </div>
		        </spring:bind>		

				<spring:bind path="name">
		        	<span><b>name</b></span>
		            <div class="form-group ${status.error ? 'has-error' : ''}">
		                <form:input type="text" path="name" class="form-control" placeholder="Name"
		                            autofocus="true"></form:input>
		                <form:errors path="username"></form:errors>
		            </div>
		        </spring:bind>

		        <spring:bind path="usertype">
		        	<span><b>player type:</b></span>
		            <div class="form-group ${status.error ? 'has-error' : ''}">
		            	<div style="line-height: 48px;">
		            	<form:radiobutton path="usertype" value="player" checked="checked"/>Player
						<form:radiobutton path="usertype" value="host"/>Host
						</div>
		            </div>
		        </spring:bind>
		        		                		               
		      </div>
		      <div class="col-sm-4">
						
		        <spring:bind path="picture">
		        	<span><b>picture</b></span>
		            <div class="form-group ${status.error ? 'has-error' : ''}">
		                <form:input type="text" path="picture" class="form-control" placeholder="Picture"
		                            autofocus="true"></form:input>
		            </div>
		        </spring:bind>
		
		        <spring:bind path="bio">
		        	<span><b>bio</b></span>
		            <div class="form-group ${status.error ? 'has-error' : ''}">
		                <form:input type="text" path="bio" class="form-control" placeholder="Bio"
		                            autofocus="true"></form:input>
		            </div>
		        </spring:bind>
		
		        <spring:bind path="location">
		        	<span><b>location</b></span>
		            <div class="form-group ${status.error ? 'has-error' : ''}">
		                <form:input type="text" path="location" class="form-control" placeholder="Location"
		                            autofocus="true"></form:input>
		            </div>
		        </spring:bind>
		        
		        <spring:bind path="contact">
		        	<span><b>contact information</b></span>
		            <div class="form-group ${status.error ? 'has-error' : ''}">
		                <form:input type="text" path="contact" class="form-control" placeholder="Contact Information"
		                            autofocus="true"></form:input>
		            </div>
		        </spring:bind>  	        		                
		      </div>  
		      <div class="col-sm-4">
		      	<a href="${contextPath}/delete-profile">Delete profile</a>
		      </div> 
		            
        </div>
        <div class="row">
        	<div class="col-sm-4"><button class="btn btn-lg btn-primary btn-block" type="submit">Save</button></div>
        	<div class="col-sm-4"><a style="margin-top: 10px;" href="${contextPath}/" class="btn btn-lg btn-block">Cancel</a></div>
        </div>
        

        
    </form:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
