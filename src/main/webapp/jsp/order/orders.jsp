<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Orders</title>
</head>
<body>
	<table>
		<th>#</th>
		<th>Id</th>
		<th>Client</th>
		<th>Total coast</th>
		<th>Status</th>
		<th>Pharmacist</th>
		<c:forEach items="${orders}" var="order" varStatus="counter">
			<tr>
				<td>${counter.count}</td>
				<td><a href="controller?command=order&id=${order.id}">order-${order.id}</a></td>
				<td><a href="controller?command=client&id=${order.client.id}">${order.client.firstName}  ${order.client.lastName}</a></td>
				<td>${order.totalCoast}</td>
				<td>${order.status}</td>
				<td><a href="controller?command=pharmacist&id=${order.pharmacist.id}">${order.pharmacist.firstName}  ${order.pharmacist.lastName}</a></td>
			</tr>
		</c:forEach>
	</table>
	<h3><a href="/drugstore">Main page</a></h3>
</body>
</html>