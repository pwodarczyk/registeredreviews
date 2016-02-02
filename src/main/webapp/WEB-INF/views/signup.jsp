<%@ include file="/WEB-INF/views/includes/taglibs.jspf" %>
<%@page session="true"%>
<c:import url="/WEB-INF/views/includes/header.jsp">
	<c:param name="page_title" value="Login Page"/>
	<c:param name="meta_description" value="Login, Registered Reviews"/>	
	<c:param name="meta_keywords" value="reviews, registered"/>
</c:import>
<script src="/static/js/signup.js"></script>

	<h1>Spring Security Login Form (Database Authentication)</h1>

	<div id="mainWrapper">
            <div class="signup-container">
                <div>
                    <div class="signup-form">
                        <form id="SignupForm" action="/signup" method="post" class="form">
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                    <p>${param.error}</p>
                                </div>
                            </c:if>
                            <c:if test="${param.logout != null}">
                                <div class="alert alert-success">
                                    <p>You have been logged out successfully.</p>
                                </div>
                            </c:if>
                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="username"><i class=""></i></label>
                                <input type="email" class="form-control" id="email" name="email" placeholder="Enter Email" required>
                            </div>
                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="password"><i class=""></i></label> 
                                <input type="password" class="form-control" id="password1" name="password1" placeholder="Enter Password" required>
                            </div>
                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
                                <input type="password" class="form-control" id="password2" name="password2" placeholder="Enter Password Again" required>
                            </div>

                                 
                            <div class="form-actions">
                                <input type="submit"
                                    class="btn btn-block btn-primary btn-default" value="Sign Up">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

<c:import url="/WEB-INF/views/includes/footer.jsp"/>
