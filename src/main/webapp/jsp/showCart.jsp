<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <title>Cart</title>
</head>
<body>
  <h1>Cart</h1>
  <c:choose>
    <c:when test="${drugs.size()>0}">
  <table>
    <th>#</th>
    <th>Id</th>
    <th>Name</th>
    <th>Release Form</th>
    <th>Price</th>
    <th>Add Drug</th>
    <th>Quantity</th>
    <th>Remove Drug</th>
    <c:forEach items="${drugs}" var="entry" varStatus="counter">
      <tr>
        <td>${counter.count}</td>
        <td><a href="controller?command=drug&id=${entry.key.id}">${entry.key.id}</td>
        <td><a href="controller?command=drug&id=${entry.key.id}">${entry.key.name}</a></td>
        <td><a href="controller?command=drug&id=${entry.key.id}">${entry.key.releaseForm}</a></td>
        <td><a href="controller?command=drug&id=${entry.key.id}">${entry.key.price}</a></td>
        <td>
          <form method="post" action="controller">
            <input type="hidden" name="command" value="remove_to_cart">
            <input type="hidden" name="drugId" value="${entry.key.id}">
            <input type="submit" value="-">
          </form>
        </td>
        <td>
            ${entry.value}
        </td>
        <td>
          <form method="post" action="controller">
            <input type="hidden" name="command" value="add_to_cart">
            <input type="hidden" name="drugId" value="${entry.key.id}">
            <input type="submit" value="+">
          </form>
        </td>
      </tr>
    </c:forEach>
  </table>
  <h1>TOTAL COAST: ${totalCoast} </h1>
    </c:when>
    <c:otherwise>
      <h3>NO PRODUCTS IN THE CART</h3>
    </c:otherwise>
  </c:choose>
  <li><a href="controller?command=drugs">All drugs</a></li>
</body>
</html>
