<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <title>Cart</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>
  <h1>Cart</h1>
<c:if test="${requestScope.cart == null}">
  <p>You haven't added anything in your cart</p>
</c:if>
<c:if test="${requestScope.cart != null}">
  <table>
    <th>#</th>
    <th>Id</th>
    <th>Name</th>
    <th>Release Form</th>
    <th>Price</th>
    <th>Add Drug</th>
    <th>Quantity</th>
    <th>Remove Drug</th>
    <c:forEach items="${requestScope.cart.drugs}" var="entry" varStatus="counter">
      <tr>
        <td>${counter.count}</td>
        <td><a href="controller?command=drug&id=${entry.key.id}">${entry.key.id}</td>
        <td><a href="controller?command=drug&id=${entry.key.id}">${entry.key.name}</a></td>
        <td>${entry.key.releaseForm}</td>
        <td>${entry.key.price}</td>
        <td>
          <form method="post" action="controller">
            <input type="hidden" name="command" value="remove_from_cart">
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
  <h3>TOTAL COAST: ${requestScope.cart.totalCoast} </h3>
      <form method="post" action="controller">
        <input type="hidden" name="command" value="create_order">
        <input type="hidden" name="drugId" value="${entry.key.id}">
        <input type="submit" value="Buy">
      </form>
</c:if>
</body>
</html>
