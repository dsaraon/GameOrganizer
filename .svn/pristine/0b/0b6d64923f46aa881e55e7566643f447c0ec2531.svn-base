<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<jsp:include page="common/head.jsp"/>



<!-- BEGIN CONTENT-->

<div class="container">

    <form:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading">Create your account</h2>
        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="username" class="form-control" placeholder="Username"
                            autofocus="true"></form:input>
                <form:errors path="username"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="useremail">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="useremail" class="form-control" placeholder="Email"></form:input>
                <form:errors path="useremail"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="usertype">
            <div class="form-group ${status.error ? 'has-error' : ''}">
            	<form:radiobutton path="usertype" value="player" checked="checked"/>Player
				<form:radiobutton path="usertype" value="host"/>Host
            </div>
        </spring:bind>


        <spring:bind path="city">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input id="user_city" type="hidden" path="city" class="form-control"></form:input>
                <form:errors path="city"></form:errors>
            </div>
        </spring:bind>
        
        <spring:bind path="location">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input id="user_location" type="hidden" path="location" class="form-control"></form:input>
                <form:errors path="location"></form:errors>
            </div>
        </spring:bind>        

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="passwordConfirm">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="passwordConfirm" class="form-control"
                            placeholder="Confirm your password"></form:input>
                <form:errors path="passwordConfirm"></form:errors>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>



<script>
window.onload = function() {
  var startPos;
  var geoOptions = {
    enableHighAccuracy: true
}

  var geoSuccess = function(position) {
    startPos = position;    
    $('#user_location').val(startPos.coords.latitude + ',' + startPos.coords.longitude);
    
	$.ajax({
	   url: 'http://maps.googleapis.com/maps/api/geocode/json?latlng='+startPos.coords.latitude+','+startPos.coords.longitude+'&sensor=true',
	   type: 'GET',
	   error: function(data) {
	      console.log(data);
	   },
	   success: function(data) {
	   	   $('#user_city').val(data.results[1].formatted_address);
	   	   console.log(data.results[1].formatted_address);  
	   }
	});
    
    
    
};
  var geoError = function(error) {
    console.log('Error occurred. Error code: ' + error.code);
    // error.code can be:
    //   0: unknown error
    //   1: permission denied
    //   2: position unavailable (error response from location provider)
    //   3: timed out
};

  navigator.geolocation.getCurrentPosition(geoSuccess, geoError, geoOptions);
};
</script>





<!-- END CONTENT-->

<jsp:include page="common/footer.jsp" />
