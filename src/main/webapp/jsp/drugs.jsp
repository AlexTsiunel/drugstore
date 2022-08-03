<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Drugs</title>
</head>
<body>
	<table>
		<th>#</th>
		<th>Id</th>
		<th>Name</th>
		<th>Release Form</th>
		<th>Price</th>
		<c:forEach items="${drugs}" var="drug" varStatus="counter">
			<tr>
				<td>${counter.count}</td>
				<td><a href="controller?command=drug&id=${drug.id}">${drug.id}</a></td>
				<td><a href="controller?command=drug&id=${drug.id}">${drug.name}</a></td>
				<td><a href="controller?command=drug&id=${drug.id}">${drug.releaseForm}</a></td>
				<td><a href="controller?command=drug&id=${drug.id}">${drug.price}</a></td>
			</tr>
		</c:forEach>
	</table>
	<h3><a href="/drugstore">Main page</a></h3>
</body>
</html>