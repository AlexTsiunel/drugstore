<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>DrugStore</title>
  </head>
  <body>
<c:if test="${sessionScope.client == null}">
  <h1>Welcome to DrugStore, Dear friend !!!</h1>
</c:if>
<c:if test="${sessionScope.client != null}">
  <h1>Welcome to DrugStore, ${sessionScope.client.firstName} ${sessionScope.client.lastName} !!!</h1>
</c:if>
    <ul>
      <li><a href="controller?command=create_client_form">Sign up</a></li>
      <li><a href="controller?command=login_form">Sign in</a></li>
      <li><a href="controller?command=show_cart">Cart</a></li>
      <li><a href="controller?command=clients">All clients</a></li>
      <li><a href="controller?command=pharmacists">All pharmacists</a></li>
      <li><a href="controller?command=drugs">All drugs</a></li>
      <li><a href="controller?command=orders">All orders</a></li>
      <li><a href="controller?command=create_drug_form">Add new drug</a></li>
    </ul>
  </body>
</html>
