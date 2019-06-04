<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Welcome to home

	<hr>
	<p>
		<security:authentication property="principal.username" />
	</p>
	<p>
		<security:authentication property="principal.authorities" />
	</p>
	<hr>
	<security:authorize access="hasRole('MANAGER')">
		<p>
			<a href="${pageContext.request.contextPath}/manager">MANAGER
				PORTAL</a> (FOR MANAGRS ONLY)
		</p>
	</security:authorize>
	<hr>
	<security:authorize access="hasRole('ADMIN')">
		<p>
			<a href="${pageContext.request.contextPath}/admin">ADMIN PORTAL</a>
			(FOR ADMINS ONLY)
		</p>
	</security:authorize>
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout" />
	</form:form>

</body>
</html>