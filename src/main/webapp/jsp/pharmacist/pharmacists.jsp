<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Pharmacists</title>
</head>
<body>
	<table>
		<th>#</th>
		<th>Id</th>
		<th>firstName</th>
		<th>lastName</th>
		<th>email</th>
		<c:forEach items="${pharmacists}" var="pharmacist" varStatus="counter">
			<tr>
				<td>${counter.count}</td>
				<td><a href="controller?command=pharmacist&id=${pharmacist.id}">${pharmacist.id}</a></td>
				<td><a href="controller?command=pharmacist&id=${pharmacist.id}">${pharmacist.firstName}</a></td>
				<td><a href="controller?command=pharmacist&id=${pharmacist.id}">${pharmacist.lastName}</a></td>
				<td><a href="controller?command=pharmacist&id=${pharmacist.id}">${pharmacist.email}</a></td>
			</tr>
		</c:forEach>
	</table>
	<h3><a href="/drugstore">Main page</a></h3>
</body>
</html>