<%@ include file="/WEB-INF/views/includes/taglibs.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="/WEB-INF/views/includes/header.jsp">
	<c:param name="page_title" value="Request a Quote"/>
	<c:param name="meta_description" value="Request a Quote Page, Registered Reviews"/>	
	<c:param name="meta_keywords" value="reviews, registered"/>
</c:import>


<div>


	<form:form id="QuoteRequestForm" action="/quote/new" method="post" modelAttribute="quoteRequest">
		<c:if test="${param.error != null}">
			<div class="alert alert-danger">
				<p>${param.error}</p>
			</div>
		</c:if>

		<form:input path="businessId" id="businessId" type="hidden" />

		<label for="name">Name: </label>
		<form:input path="name" id="name" />
		<form:errors path="name" cssClass="error" />
		
		<label for="email">Email: </label>
		<form:input type="email" path="email" id="email" />
		<form:errors path="email" cssClass="error" />
		
		<label for="serviceRequested">Service Requested: </label>
		<form:input path="serviceRequested" id="serviceRequested" />
		<form:errors path="serviceRequested" cssClass="error" />
		
		<label for="message">Message: </label>
		<form:input path="message" id="message" />
		<form:errors path="message" cssClass="error" />
		
		<input type="submit" value="Send Request" />
	</form:form>
</div>


<c:import url="/WEB-INF/views/includes/footer.jsp"/>
