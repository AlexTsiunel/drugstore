<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Drugs</title>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<jsp:include page="../pageNavigation.jsp"/>
	<table>
		<th>#</th>
		<th>Id</th>
		<th>Name</th>
		<th>Release Form</th>
		<th>Price</th>
		<th>Action</th>
		<c:forEach items="${drugs}" var="drug" varStatus="counter">
			<tr>
				<td>${counter.count}</td>
				<td><a href="controller?command=drug&id=${drug.id}">${drug.id}</a></td>
				<td><a href="controller?command=drug&id=${drug.id}">${drug.name}</a></td>
				<td><a href="controller?command=drug&id=${drug.id}">${drug.releaseForm}</a></td>
				<td><a href="controller?command=drug&id=${drug.id}">${drug.price}</a></td>
				<td>
					<form method="post" action="controller">
						<input type="hidden" name="command" value="add_to_cart">
						<input type="hidden" name="drugId" value="${drug.id}">
						<input type="hidden" name="redirect" value="?command=drugs">
						<input type="submit" value="Add to cart">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>