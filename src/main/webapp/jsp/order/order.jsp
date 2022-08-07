<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Order</title>
  </head>
  <body>
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
        <td><a href="controller?command=client&id=${order.client.id}">${order.client.firstName}   ${order.client.lastName}</a></td>
        <td>
      <table>
        <tr><th>#</th><th>Release form</th><th>Price</th><th>Quantity</th></tr>
        <c:forEach items="${order.drugs}" var="entry" varStatus="counter">
              <tr><td>${counter.count}</td> <td>${entry.key.releaseForm}</td><td>${entry.key.price} byn</td><td>${entry.value}</td></tr>
            </c:forEach>
          </table>
          TOTAL PRICE: ${order.totalCoast} BYN
        </td>
        <td>${order.status}</td>
        <td><a href="controller?command=pharmacist&id=${order.pharmacist.id}">${order.pharmacist.firstName}   ${order.pharmacist.lastName}</a></td>
      </tr>
    </table>
    <h3><a href="controller?command=orders">All orders</a></h3>
  </body>
</html>
