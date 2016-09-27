<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<jsp:include page="common/head.jsp"/>



<!-- BEGIN CONTENT-->

<div class="container myprofile">

    <form:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading">Change password</h2>
        
        <div class="row">
        	<div class="col-sm-4">
				
		        <spring:bind path="password">
		            <div class="form-group ${status.error ? 'has-error' : ''}">
		                <form:input type="password" path="password" class="form-control" placeholder="Enter new password"></form:input>
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
        		        		                		                
		      </div>

		      <div class="col-sm-4"></div>   
		            
        </div>
        <div class="row">
        	<div class="col-sm-4"><button class="btn btn-lg btn-primary btn-block" type="submit">Save</button></div>
        	<div class="col-sm-4"><a style="margin-top: 10px;" href="${contextPath}/" class="btn btn-lg btn-block">Cancel</a></div>
        </div>
  
    </form:form>

</div>

<!-- END CONTENT-->

<jsp:include page="common/footer.jsp" />
