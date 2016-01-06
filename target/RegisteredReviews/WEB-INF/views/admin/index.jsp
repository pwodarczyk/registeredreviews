<%@ include file="/WEB-INF/views/includes/taglibs.jspf" %>
<%@page session="true"%>
<c:import url="/WEB-INF/views/includes/header.jsp">
	<c:param name="page_title" value="Admin Page"/>
	<c:param name="meta_description" value="Admin, Registered Reviews"/>	
	<c:param name="meta_keywords" value="reviews, registered"/>
</c:import>

<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>

	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>
	
	
<c:import url="/WEB-INF/views/includes/footer.jsp"/>