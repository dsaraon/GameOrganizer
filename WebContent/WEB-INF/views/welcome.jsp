<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<jsp:include page="common/head.jsp"/>



<!-- BEGIN CONTENT-->

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

<div class="container">

	
	<div class="row">
	
		<div class="col-sm-3">
			<h3>Welcome <b>${pageContext.request.userPrincipal.name}</b></h3>
			<div class="list-group">
			  	<a class="list-group-item" href="${contextPath}/update-profile">Edit profile</a>
			 	<a class="list-group-item" href="${contextPath}/change-password">Change password</a>
			  	<a class="list-group-item" onclick="document.forms['logoutForm'].submit()">Logout</a>
			</div>
			
			<c:if test="${user.usertype eq 'host'}">
			<div class="list-group">
				<a class="list-group-item" href="${contextPath}/locations">My locations</a>
			</div>
			</c:if>
			
			<div class="list-group">
				<a class="list-group-item" href="${contextPath}/events">Find games</a>
				<a class="list-group-item" href="${contextPath}/tournaments">Tournaments</a>
			</div>			
			
			<h3>My location</h3>
			${user.city}
			
		</div>
		
		<div class="col-sm-9">
			
			<c:if test="${user.usertype ne 'host'}">
			<h3>My games</h3>
			</c:if>
			<c:if test="${user.usertype eq 'host'}">
			<h3>My created games</h3>
			</c:if>


			<c:if test="${empty events}">
				<div class="alert alert-warning">
					You don't have games yet
				</div>
			</c:if>

			<c:if test="${not empty events}">  
			<table class="services table table-striped table-inverse">
				<thead>
					<tr>
						<td><b>date</b></td>
						<td><b>time</b></td>
						<td><b>location</b></td>
						<td><b>owner</b></td>
						<td><b>fee</b></td>
						<td><b>description</b></td>
						<td><b>num of players</b></td>
						<td><b>type</b></td>
						<td></td>
	                </tr>
	            </thead>
                <tbody> 
                	<c:forEach items="${events}" var="event">
					<tr>    
					  	<td align="left"><c:out value="${event.date}"/></td>                     
						<td align="left"><c:out value="${event.time}"/></td>
						<td align="left">${event.city} <a class="btn btn-primary btn-xs btn-info" target="_blank" href="http://maps.google.com/maps?q=${event.location}">map</a></td>						
						<td align="left"><c:out value="${event.owner}"/></td>
						<td align="left"><c:out value="${event.fee_person}"/></td>
						<td align="left"><c:out value="${event.description}"/></td>
						<td align="left" class="num_joined" eventname="game" eventid="${event.id}"><c:out value="${event.num_players}"/></td>
						<td align="left"><c:out value="${event.type}"/></td>
						
						<c:set var="owner" value="${event.owner}"/>
						<c:set var="username" value="${pageContext.request.userPrincipal.name}"/>
						<c:set var="eventList" value="${playerInfo.games_ids}"/>
						<c:set var="eventId" value="${event.id}"/>	
					
						<td align="left">
							<c:if test="${userType ne 'host'}">
							<c:choose>
								<c:when test="${fn:contains(eventList, eventId)}">
									<a class="btn btn-xs btn-primary" href="${contextPath}/leave-game?id=${event.id}">leave</a>
								</c:when>    
								<c:otherwise>
									<a class="btn btn-xs btn-primary" href="${contextPath}/join-game?id=${event.id}">join</a>
								</c:otherwise>
							</c:choose>	
							</c:if>
							<c:if test="${owner eq username}">
								<a class="btn btn-primary btn-xs btn-warning" href="${contextPath}/update-event?id=${event.id}">upd</a>
								<a class="btn btn-primary btn-xs btn-danger" href="${contextPath}/delete-event?id=${event.id}">del</a>
							</c:if>
						 </td>
						
					</tr>
					</c:forEach>   
				</tbody>
			</table>
			</c:if>
			
		
		<br><br>
		
		
			<c:if test="${user.usertype ne 'host'}">
			<h3>My tournaments</h3>
			</c:if>
			<c:if test="${user.usertype eq 'host'}">
			<h3>My created tournaments</h3>
			</c:if>


			<c:if test="${empty tournaments}">
				<div class="alert alert-warning">
					You don't have tournaments yet
				</div>
			</c:if>

			<c:if test="${not empty tournaments}">  
			<table class="services table table-striped table-inverse">
				<thead>
					<tr>
						<td><b>date</b></td>
						<td><b>time</b></td>
						<td><b>location</b></td>
						<td><b>owner</b></td>
						<td><b>num teams</b></td>
						<td><b>num per team</b></td>
						<td><b>num of players</b></td>
						<td></td>
	                </tr>
	            </thead>
                <tbody> 
                	<c:forEach items="${tournaments}" var="tournaments">
					<tr>    
					  	<td align="left"><c:out value="${tournaments.date}"/></td>                     
						<td align="left"><c:out value="${tournaments.time}"/></td>
						<td align="left">${tournaments.city} <a class="btn btn-primary btn-xs btn-info" target="_blank" href="http://maps.google.com/maps?q=${tournaments.location}">map</a></td>
						<td align="left"><c:out value="${tournaments.owner}"/></td>
						<td align="left"><c:out value="${tournaments.num_teams}"/></td>
						<td align="left"><c:out value="${tournaments.num_per_team}"/></td>
												
						<c:set var="multRes" value="${tournaments.num_teams * tournaments.num_per_team}"/>
						<td align="left" class="num_joined" eventname="tournament" eventid="${tournaments.id}"><c:out value="${multRes}"/></td>
						
						<c:set var="owner" value="${tournaments.owner}"/>
						<c:set var="username" value="${pageContext.request.userPrincipal.name}"/>
						<c:set var="tournamentList" value="${playerInfo.tournaments_ids}"/>
						<c:set var="tournamentId" value="${tournaments.id}"/>	
					
						<td align="left">
							<c:choose>
								<c:when test="${fn:contains(tournamentList, tournamentId)}">
									<a class="btn btn-xs btn-primary" href="${contextPath}/leave-tournament?id=${tournaments.id}">leave</a>
								</c:when>    
								<c:otherwise>
									<a class="btn btn-xs btn-primary" href="${contextPath}/join-tournament?id=${tournaments.id}">join</a>
								</c:otherwise>
							</c:choose>	
						
							<c:if test="${owner eq username}">
								<a class="btn btn-primary btn-xs btn-warning" href="${contextPath}/update-tournament?id=${tournaments.id}">upd</a>
								<a class="btn btn-primary btn-xs btn-danger" href="${contextPath}/delete-tournament?id=${tournaments.id}">del</a>
							</c:if>
						 </td>
						
					</tr>
					</c:forEach>   
				</tbody>
			</table>
			</c:if>		
		
		</div>
		
	
	</div>

</div>

</c:if>
<!-- END CONTENT-->

<jsp:include page="common/footer.jsp" />




