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
 
    var centerOfMap = new google.maps.LatLng(${locationForm.coordinates});
 
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
	   	   $('#event_city').val(data.results[1].formatted_address);
	   	   console.log(data.results[1].formatted_address);  
	   }
	});

}
        
google.maps.event.addDomListener(window, 'load', initMap);
</script>




<div class="container myprofile">

<form:form method="POST" modelAttribute="locationForm" class="form-signin">
        <h2 class="form-signin-heading">New location</h2>
        
        <div class="row">
        	<div class="col-sm-4">
        	     		        
		        <spring:bind path="name">
		        	<span><b>name</b></span>
		            <div class="form-group ${status.error ? 'has-error' : ''}">
		                <form:input type="text" path="name" class="form-control" placeholder="name"></form:input>
						<form:errors path="name"></form:errors>
		            </div>
		        </spring:bind>				                		               
		      </div>
		      <div class="col-sm-4">
				<spring:bind path="coordinates">
		        	<span><b>coordinates</b></span>
		            <div class="form-group ${status.error ? 'has-error' : ''}">
		            	<div id="map"></div>
		                <form:input id="location_coord" type="hidden" path="coordinates" class="form-control" placeholder="coordinates"
		                            autofocus="true"></form:input>
						<form:errors path="coordinates"></form:errors>
		            </div>
		        </spring:bind>

		        						
		        <spring:bind path="text_location">
		        	<span><b>text_location</b></span>
		            <div class="form-group ${status.error ? 'has-error' : ''}">
		                <form:input id="event_city" type="text" path="text_location" class="form-control" placeholder="text_location"
		                            autofocus="true"></form:input>
		                <form:errors path="text_location"></form:errors>            
		            </div>
		        </spring:bind>		      
		      </div>
 
        </div>
        <div class="row">
        	<div class="col-sm-4"><button class="btn btn-lg btn-primary btn-block" type="submit">Save</button></div>
        	<div class="col-sm-4"><a style="margin-top: 10px;" href="${contextPath}/locations" class="btn btn-lg btn-block">Cancel</a></div>
        </div>
        

        
    </form:form>


</div>

<!-- END CONTENT-->

<jsp:include page="common/footer.jsp" />
