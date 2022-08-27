<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
</head>
<body>
  <h1>Login</h1>
  <c:if test="${requestScope.message != null}">
    <h3>${requestScope.message}</h3>
  </c:if>
  <form method="post" action="controller">
    <input name="command" type="hidden" value="login"/>
    <label for="email-input">Email</label><input id="email-input" name="email" type="email"/><br/>
    <label for="password-input">Password</label><input id="password-input" name="password" type="text" min="6"/><br/>
    <input type="submit" value="SIGN IN">
  </form>
</body>
</html>
