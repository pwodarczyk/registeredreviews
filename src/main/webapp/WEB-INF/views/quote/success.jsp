<%@ include file="/WEB-INF/views/includes/taglibs.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="/WEB-INF/views/includes/header.jsp">
	<c:param name="page_title" value="Request a Quote"/>
	<c:param name="meta_description" value="Request a Quote Page, Registered Reviews"/>	
	<c:param name="meta_keywords" value="reviews, registered"/>
</c:import>

<div>
	message : ${success}
	<br/>
	<br/>
	<br/>
	Go back to <a href="<c:url value='/' />">Home</a>
</div>

<c:import url="/WEB-INF/views/includes/footer.jsp"/>