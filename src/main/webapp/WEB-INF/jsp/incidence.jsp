<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Registration Form</title>
<style type="text/css">
	label {
		display: inline-block;
		width: 200px;
		margin: 5px;
		text-align: left;
	}
	button {
		padding: 10px;
		margin: 10px;
	}
</style>
</head>
<body>
	<div align="center">
		<h2>Country Incidence</h2>
		<form:form action="incidence" method="post" modelAttribute="countryIncidence">
			<form:label path="">Country:</form:label>
			<form:input path="name"/>
			<br/>
			<form:button>Search</form:button>
            <br/>
		    <c:forEach items="${countryIncidenceList}" var="countryWithIncidence">
                <c:out value="${countryWithIncidence.name}" />
                <c:out value="${countryWithIncidence.incidence}" />
                <br/>
            </c:forEach>
		</form:form>
	</div>
</body>
</html>