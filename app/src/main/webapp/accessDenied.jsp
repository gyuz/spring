<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Crud Application</title>
    <link href="<c:url value='/css/styles.css' />" rel="stylesheet"/>
</head>
<body>
	<h1>HTTP Status 403 - Access is denied</h1>
	<c:choose>
		<c:when test="${empty username}">
			<h2>You do not have permission to access this page!</h2>
		</c:when>
		<c:otherwise>
			<h2>Username : ${username} <br/>You do not have permission to access this page!</h2>
		</c:otherwise>
	</c:choose>
	<div>
        <form action="/logout" method="post" id="logoutForm">
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button type="submit" name="logout" class="lang"><spring:message code="lbl.logout" text="LOGOUT" /></button> 
	    </form>
    </div>
</body>
</html>
