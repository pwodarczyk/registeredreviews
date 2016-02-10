<%@ include file="/WEB-INF/views/includes/taglibs.jspf" %>

<c:import url="/WEB-INF/views/includes/header.jsp">
	<c:param name="page_title" value="Home"/>
	<c:param name="meta_description" value="Home Page, Registered Reviews"/>	
	<c:param name="meta_keywords" value="reviews, registered"/>
</c:import>


<div>
    <p>Search:</p><input type ="text" name="search"/>
</div>


<c:import url="/WEB-INF/views/includes/footer.jsp"/>
