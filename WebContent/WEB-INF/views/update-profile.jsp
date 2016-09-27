<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<jsp:include page="common/head.jsp"/>

<style type="text/css">
	#map{ width:400px; height: 300px; }
</style>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDYaVAH5LTFQdcyfEyLcuCXNEEXd7xzWE8"></script>


<!-- BEGIN CONTENT-->


<script>

var map;
var marker = false; 
        
function initMap() {
 
    var centerOfMap = new google.maps.LatLng(${userForm.location});
 
    var options = {
      center: centerOfMap, //Set center.
      zoom: 7 //The zoom value.
    };
 
    map = new google.maps.Map(document.getElementById('map'), options);
 
 	var marker2 = new google.maps.Marker({
          position: centerOfMap,
          map: map,
          title: 'Hello World!'
        });
 
 
    google.maps.event.addListener(map, 'click', function(event) {                
        
        var clickedLocation = event.latLng;
       
        if(marker === false){
           
            marker = new google.maps.Marker({
                position: clickedLocation,
                map: map,
                draggable: true //make it draggable
            });
            
            google.maps.event.addListener(marker, 'dragend', function(event){
                markerLocation();
            });
        } else{
            marker.setPosition(clickedLocation);
        }
        markerLocation();
    });
}
        
function markerLocation(){

    var currentLocation = marker.getPosition();
    console.log(marker);    
    document.getElementById('location_coord').value = currentLocation.lat() + "," + currentLocation.lng(); //latitude
	
	$.ajax({
	   url: 'http://maps.googleapis.com/maps/api/geocode/json?latlng='+currentLocation.lat()+','+currentLocation.lng()+'&sensor=true',
	   type: 'GET',
	   error: function(data) {
	      console.log(data);
	   },
	   success: function(data) {
	   	   $('#user_city').val(data.results[1].formatted_address);
	   	   console.log(data.results[1].formatted_address);  
	   }
	});

}
        
google.maps.event.addDomListener(window, 'load', initMap);
</script>

<!-- BEGIN CONTENT-->

<div class="container myprofile">

    <form:form method="POST" modelAttribute="userForm" class="form-signin">
        
        <div class="row">
        	<div class="col-sm-8"><h2 class="form-signin-heading">My profile <a class="btn btn-primary btn-xs btn-danger" href="${contextPath}/delete-profile">delete profile</a></h2></div>
        	
        </div>
        
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
<!-- 
		        <spring:bind path="usertype">
		        	<span><b>player type:</b></span>
		            <div class="form-group ${status.error ? 'has-error' : ''}">
		            	<div style="line-height: 48px;">
		            	<form:radiobutton path="usertype" value="player"/>Player
						<form:radiobutton path="usertype" value="host"/>Host
						</div>
		            </div>
		        </spring:bind>
 -->
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
		                <form:textarea type="text" path="bio" class="form-control" placeholder="Bio"
		                            autofocus="true"></form:textarea>
		            </div>
		        </spring:bind>
		        
		        <spring:bind path="contact">
		        	<span><b>contact information</b></span>
		            <div class="form-group ${status.error ? 'has-error' : ''}">
		                <form:textarea type="text" path="contact" class="form-control" placeholder="Contact Information"
		                            autofocus="true"></form:textarea>
		            </div>
		        </spring:bind> 		        		                		               
		      </div>
		      <div class="col-sm-4">
		      	
		      	<spring:bind path="location">
		        	<span><b>location</b></span>
		            <div class="form-group ${status.error ? 'has-error' : ''}">
		            	<div id="map"></div>
		                <form:input id="location_coord" type="hidden" path="location" class="form-control" placeholder="location"
		                            autofocus="true"></form:input>

		            	<form:errors path="location"></form:errors>
		            </div>
		            
		        </spring:bind>
		            	
				<spring:bind path="city">
		            <div class="form-group ${status.error ? 'has-error' : ''}">
		                <form:input id="user_city" type="text" path="city" class="form-control" placeholder="select place on the map"
		                            autofocus="true"></form:input>
		            	<form:errors path="city"></form:errors>
		            </div>
		        </spring:bind>
		      	
		      </div> 
		            
        </div>
        <div class="row">
        	<div class="col-sm-4"><button class="btn btn-lg btn-primary btn-block" type="submit">Save</button></div>
        	<div class="col-sm-4"><a style="margin-top: 10px;" href="${contextPath}/" class="btn btn-lg btn-block">Cancel</a></div>
        </div>
        

        
    </form:form>

</div>





<!-- END CONTENT-->

<jsp:include page="common/footer.jsp" />
