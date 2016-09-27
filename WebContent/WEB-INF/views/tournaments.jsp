<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<jsp:include page="common/head.jsp"/>



<!-- BEGIN CONTENT-->


<div class="container myprofile">
	
	<c:if test="${not empty error}">
		<div class="alert alert-warning">
			${error}
		</div>
	</c:if>

	<h2 class="form-signin-heading">Tournaments</h2>
        
    <br>    
    <div class="row">
       	
    
    	<div class="col-sm-2">
    		<div class='input-group date' id='datetimepicker1'>
                <input id="date1" type="text" class="form-control" placeholder="date" autofocus="false"></input>

                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
    	</div>
    	<div class="col-sm-2">
    		<div class='input-group date' id='datetimepicker2'>
                <input id="time1" type="text" class="form-control" placeholder="time" autofocus="false"></input>

                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
    	</div>
    	<div class="col-sm-2">
                <a id="show" href="${contextPath}/tournaments" class="btn btn-sm btn-primary">Show</a>
    	</div>    	
    </div>    
    <br>    
        
        
        
    <div class="row">
		<div class="col-sm-12">  
			<c:if test="${not empty tournamentForm}">
			<table class="services table table-striped table-inverse">
				<thead>
					<tr>
						<td><b>date</b></td>
						<td><b>time</b></td>
						<td><b>owner</b></td>
						<td><b>location</b></td>
						<td><b>number of teams</b></td>
						<td><b>number per team</b></td>
						<td><b>num of players</b></td>
						<td></td>
	                </tr>
	            </thead>
                <tbody>
					<c:forEach items="${tournamentForm}" var="tournament">  
					<tr>  
					  	<td align="left"><c:out value="${tournament.date}"/></td>                     
						<td align="left"><c:out value="${tournament.time}"/></td>
						<td align="left"><c:out value="${tournament.owner}"/></td>
						<td align="left">${tournament.city} <a class="btn btn-primary btn-xs btn-info" target="_blank" href="http://maps.google.com/maps?q=${tournament.location}">map</a></td>
						<td align="left"><c:out value="${tournament.num_teams}"/></td>
						<td align="left"><c:out value="${tournament.num_per_team}"/></td>
						
						<c:set var="multRes" value="${tournament.num_teams * tournament.num_per_team}"/>
						<td align="left" class="num_joined" eventname="tournament" eventid="${tournament.id}"><c:out value="${multRes}"/></td>
						
						<c:set var="owner" value="${tournament.owner}"/>
						<c:set var="username" value="${pageContext.request.userPrincipal.name}"/>
						<c:set var="tournamentList" value="${playerInfo.tournaments_ids}"/>
						<c:set var="tournamentId" value="${tournament.id}"/>	
					
						<td align="left">
							<c:choose>
								<c:when test="${fn:contains(tournamentList, tournamentId)}">
									<a class="btn btn-xs btn-primary" href="${contextPath}/leave-tournament?id=${tournament.id}">leave</a>
								</c:when>    
								<c:otherwise>
									<a class="btn btn-xs btn-primary" href="${contextPath}/join-tournament?id=${tournament.id}">join</a>
								</c:otherwise>
							</c:choose>	
						
							<c:if test="${owner eq username}">
								<a class="btn btn-primary btn-xs btn-warning" href="${contextPath}/update-tournament?id=${tournament.id}">upd</a>
								<a class="btn btn-primary btn-xs btn-danger" href="${contextPath}/delete-tournament?id=${tournament.id}">del</a>
							</c:if>
						 </td>
						  
						 
					</tr>  
					</c:forEach>   
				</tbody>
			</table>
			</c:if>
			
			<c:if test="${empty tournamentForm}">
				<div class="alert alert-warning">
					Tournaments not found
				</div>
			</c:if>
		</div>      		                
	</div>  
	<div class="row">
		<div class="col-sm-2"><a href="${contextPath}/create-tournament" class="btn btn-lg btn-primary btn-block">Create</a></div>
		<div class="col-sm-2"><a href="${contextPath}/" class="btn btn-lg btn-link">Cancel</a></div>
	</div>
</div>

        <script type="text/javascript">
 	
        $( document ).ready(function() {
        	
        	var resDate = '';
        	var resTime = '';
        	
			var s = $('#datetimepicker1').datetimepicker({
			format: 'MM/DD/YYYY'
			});
				  
				  
			$('#datetimepicker2').datetimepicker({
			format: 'hh:mm A'
			});
			
			
			$("#datetimepicker1").on("dp.change", function(e) {
			
				var s = window.location.href;
				var sss = new Date(e.date);
				var currentMonth = sss.getUTCMonth();
				if (currentMonth < 10) { currentMonth = '0' + (currentMonth+1); }
				var date1 = currentMonth + "/" + sss.getDate() + "/" + sss.getUTCFullYear();				
				resDate = date1;
        	});
			
			$("#datetimepicker2").on("dp.change", function(e) {
					
				var s = window.location.href;				
				var sss = new Date(e.date);
				var time1 = formatAMPM(sss);
				resTime = time1;

			});
			  
			$('#show').click(function(e){
				e.preventDefault();
				
				var url;
				
				console.log(resTime);
				
				if (resTime === 'undefined') {
					resTime = "";
				}
				
				if (resDate === 'undefined') {
					resDate = "";
				}
				
				url = '?date='+resDate+'&time='+resTime;
				window.location.href=location.protocol + '//' + location.host + location.pathname + url;
			})  
			  
			  
		function formatAMPM(date) {
		  	var hours = date.getHours();
		 	var minutes = date.getMinutes();
		  	var ampm = hours >= 12 ? 'PM' : 'AM';
		  	hours = hours % 12;
		  	hours = hours ? hours : 12; 
		  	minutes = minutes < 10 ? '0'+minutes : minutes;
		  	var strTime = hours + ':' + minutes + ' ' + ampm;
		  	return strTime;
		}
			   	  
		});









        </script>

<!-- END CONTENT-->

<jsp:include page="common/footer.jsp" />

