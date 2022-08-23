<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Client</title>
</head>
<body>
	<table>
		<th>#</th>
		<th>Id</th>
		<th>firstName</th>
		<th>lastName</th>
		<th>email</th>
		<c:forEach items="${clients}" var="client" varStatus="counter">
			<tr>
				<td>${counter.count}</td>
				<td><a href="controller?command=client&id=${client.id}">${client.id}</a></td>
				<td><a href="controller?command=client&id=${client.id}"><c:out value="${client.firstName}"/></a></td>
				<td><a href="controller?command=client&id=${client.id}"><c:out value="${client.lastName}"/></a></td>
				<td><a href="controller?command=client&id=${client.id}"><c:out value="${client.email}"/></a></td>
				<td><a href="controller?command=edit_client_form&id=${client.id}">EDIT</a></td>

			</tr>
		</c:forEach>
	</table>
	<h3><a href="/drugstore">Main page</a></h3>
</body>
</html>