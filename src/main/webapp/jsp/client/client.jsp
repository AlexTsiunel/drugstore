<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Client</title>
  </head>
  <body>
    <h1>The Client</h1>
    <c:if test="${requestScope.message!=null}">
      <p>${requestScope.message}</p>
    </c:if>
    <h3>Id</h3>
    <p>${client.id}</p>
    <h3>First Name</h3>
    <p><c:out value="${client.firstName}"/></p>
    <h3>Last Name</h3>
    <p><c:out value="${client.lastName}"/></p>
    <h3>Email</h3>
    <p><c:out value="${client.email}"/></p>
    <h3><a href="controller?command=clients">All clients</a></h3>
  </body>
</html>
