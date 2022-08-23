<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Order</title>
  </head>
  <body>
  <jsp:include page="../navbar.jsp"/>
    <table>
      <tr>
        <th>Id</th>
        <th>Client</th>
        <th>Items</th>
        <th>Status</th>
        <th>Pharmacist</th>
      </tr>
      <tr>
        <td>${order.id}</td>
        <td><a href="controller?command=client&id=${order.client.id}"><c:out value="${order.client.firstName}"/>   <c:out value="${order.client.lastName}"/></a></td>
        <td>
      <table>
        <tr><th>#</th><th>Release form</th><th>Price</th><th>Quantity</th></tr>
        <c:forEach items="${order.drugs}" var="entry" varStatus="counter">
              <tr><td>${counter.count}</td> <td><c:out value="${entry.key.releaseForm}"/></td><td>${entry.key.price} byn</td><td>${entry.value}</td></tr>
            </c:forEach>
          </table>
          TOTAL PRICE: ${order.totalCoast} BYN
        </td>
        <td>${order.status}</td>
        <td><a href="controller?command=pharmacist&id=${order.pharmacist.id}"><c:out value="${order.pharmacist.firstName}"/>   <c:out value="${order.pharmacist.lastName}"/></a></td>
      </tr>
    </table>
  </body>
</html>
