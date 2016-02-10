<%@ include file="/WEB-INF/views/includes/taglibs.jspf" %>
<%@page session="true"%>
<c:import url="/WEB-INF/views/includes/header.jsp">
	<c:param name="page_title" value="Login Page"/>
	<c:param name="meta_description" value="Login, Registered Reviews"/>	
	<c:param name="meta_keywords" value="reviews, registered"/>
</c:import>


	<h1>Spring Security Login Form (Database Authentication)</h1>

	<div id="mainWrapper">
            <div class="login-container">
                <div class="login-card">
                    <div class="login-form">
                        <c:url var="loginUrl" value="/login" />
                        <form action="${loginUrl}" method="post" class="form-horizontal">
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                    <p>Invalid email and password.</p>
                                </div>
                            </c:if>
                            <c:if test="${param.logout != null}">
                                <div class="alert alert-success">
                                    <p>You have been logged out successfully.</p>
                                </div>
                            </c:if>
                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                                <input type="text" class="form-control" id="email" name="email" placeholder="Enter Email" required>
                            </div>
                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
                                <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
                                 
                            <div class="form-actions">
                                <input type="submit"
                                    class="btn btn-block btn-primary btn-default" value="Log in">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

<c:import url="/WEB-INF/views/includes/footer.jsp"/>
