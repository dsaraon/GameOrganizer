<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<jsp:include page="common/head.jsp"/>
<jsp:include page="common/google_map_script.jsp" />


<!-- BEGIN CONTENT-->





<div class="container myprofile">

<form:form method="POST" modelAttribute="tournamentForm" class="form-signin">
        <h2 class="form-signin-heading">Update tournament</h2>
        
        <div class="row">
        	<div class="col-sm-4">
        	
        		<spring:bind path="date">
		        	<span><b>date</b></span>
		            <div class="form-group ${status.error ? 'has-error' : ''}">
		                						
						<div class='input-group date' id='datetimepicker1'>
		                    <form:input type="text" path="date" class="form-control" placeholder="date" autofocus="true"></form:input>

		                    <span class="input-group-addon">
		                        <span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>
						<form:errors path="date"></form:errors>
						
		            </div>
		        </spring:bind>
        	
        		<spring:bind path="time">
		        	<span><b>time</b></span>
		            <div class="form-group ${status.error ? 'has-error' : ''}">
						<div class='input-group date' id='datetimepicker2'>
		                    <form:input type="text" path="time" class="form-control" placeholder="time" autofocus="true"></form:input>

		                    <span class="input-group-addon">
		                        <span class="glyphicon glyphicon-calendar"></span>
		                    </span>	     
		                </div>
		                <form:errors path="time"></form:errors>
		            </div>
		        </spring:bind>
        		        
		        <spring:bind path="num_teams">
		        	<span><b>num_teams</b></span>
		            <div class="form-group ${status.error ? 'has-error' : ''}">
		                <form:input onkeypress='return event.charCode >= 48 && event.charCode <= 57' type="text" path="num_teams" class="form-control" placeholder="num_teams"></form:input>
						<form:errors path="num_teams"></form:errors>
		            </div>
		        </spring:bind>		
	    		       	
		       	<spring:bind path="num_per_team">
		        	<span><b>num_per_team</b></span>
		            <div class="form-group ${status.error ? 'has-error' : ''}">
		                <form:input onkeypress='return event.charCode >= 48 && event.charCode <= 57' type="text" path="num_per_team" class="form-control" placeholder="num_per_team"></form:input>
						<form:errors path="num_per_team"></form:errors>
		            </div>
		        </spring:bind>	    		       	
	    		       		                		               
		      </div>
		      <div class="col-sm-4">
		      
				<div>
					<label class="radio-inline"><input type="radio" value="exist" name="location_host" checked>Choose existing place</label>
					<label class="radio-inline"><input type="radio" value="mark" name="location_host">Mark another place</label>
				</div>

				<div id="select_location">
					<div class="form-group">
					  <label for="sel1">choice location:</label>
					  <select class="form-control" id="sel1">
					  	<option disabled selected>select location</option>
					  </select>
					</div>
				</div>
				
				<div id="mark_location">
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
		                <form:input id="event_city" type="text" path="city" class="form-control" placeholder="select place on the map"
		                            autofocus="true"></form:input>
		            	<form:errors path="city"></form:errors>
		            </div>
		        </spring:bind>		            	
		        </div> 		                
		      </div>  
		            
        </div>
        <div class="row">
        	<div class="col-sm-4"><button class="btn btn-lg btn-primary btn-block" type="submit">Save</button></div>
        	<div class="col-sm-4"><a style="margin-top: 10px;" href="${contextPath}/tournaments" class="btn btn-lg btn-block">Cancel</a></div>
        </div>
        

        
    </form:form>


        <script type="text/javascript">
        	
        	google.maps.event.addDomListener(window, 'load', initMap(${tournamentForm.location})); 
        	
        	$( document ).ready(function() {
			  $('#datetimepicker1').datetimepicker({
			  format: 'MM/DD/YYYY'
			  });
			  
			  
			  $('#datetimepicker2').datetimepicker({
			  format: 'hh:mm A'
			  });
			});

        </script>


</div>

<!-- END CONTENT-->

<jsp:include page="common/footer.jsp" />
