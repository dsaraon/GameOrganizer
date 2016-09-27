<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<jsp:include page="common/head.jsp"/>



<!-- BEGIN CONTENT-->


<div class="container myprofile">
	
	<h2 class="form-signin-heading">My Locations</h2>
        
    <br>       
    <div class="row">
		<div class="col-sm-12">  
			<c:if test="${not empty locationForm}">
			<table class="services table table-striped table-inverse">
				<thead>
					<tr>
						<td><b>name</b></td>
						<td><b>location info</b></td>
						<td></td>
	                </tr>
	            </thead>
                <tbody>
					<c:forEach items="${locationForm}" var="location">  
					<tr>                      
						<td align="left"><c:out value="${location.name}"/></td>
						<td align="left">${location.text_location} <a class="btn btn-primary btn-xs btn-info" target="_blank" href="http://maps.google.com/maps?q=${location.coordinates}">map</a></td>
	
						
						<c:set var="owner" value="${location.owner}"/>
						<c:set var="username" value="${pageContext.request.userPrincipal.name}"/>
					
						<td align="left">
							<c:if test="${owner eq username}">
								<a class="btn btn-primary btn-xs btn-warning" href="${contextPath}/update-location?id=${location.id}">upd</a>
								<a class="btn btn-primary btn-xs btn-danger" href="${contextPath}/delete-location?id=${location.id}">del</a>
							</c:if>
						 </td>
						
						
					</tr>  
					</c:forEach>   
				</tbody>
			</table>
			</c:if>
			
			<c:if test="${empty locationForm}">
				<div class="alert alert-warning">
					Your location not found
				</div>
			</c:if>
		</div>      		                
	</div>  
	<div class="row">
		<div class="col-sm-2"><a href="${contextPath}/create-location" class="btn btn-lg btn-primary btn-block">Create</a></div>
		<div class="col-sm-2"><a href="${contextPath}/" class="btn btn-lg btn-link">Cancel</a></div>
	</div>
</div>


<!-- END CONTENT-->

<jsp:include page="common/footer.jsp" />

