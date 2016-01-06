<%@ include file="/WEB-INF/views/includes/taglibs.jspf" %>
<html>
  <head>
  
	<meta charset="UTF-8">
	<meta name="description" content="${param.meta_description}">
	<meta name="keywords" content="${param.meta_keywords}">
	<link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
	<title>${param.page_title}</title>
  </head>

<body>
<div class="logo">
	<a href="/">
	<img alt="registered reviews logo" src="<c:url value='/static/images/registered-reviews-logo.png' />">
	</a>
</div>
<div class="login">
	<a href="/login">
	Login
	</a>
</div>