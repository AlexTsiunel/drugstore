<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Edit client</title>
  </head>
  <body>
    <h1>Edit client</h1>
    <form method="post" action="controller">
      <input name="command" type="hidden" value="create_client"/>
      <input name="id" type="hidden" value="${requestScope.client.id}"/>
      <label for="firstName-input">First name</label><input id="firstName-input" name="firstName" type="text" value="${requestScope.client.firstName}"/><br/>
      <label for="lastName-input">Last name</label><input id="lastName-input" name="lastName" type="text" value="${requestScope.client.lastName}"/><br/>
      <label for="email-input">Email</label><input id="email-input" name="email" type="email" value="${requestScope.client.email}"/><br/>
      <label for="password-input">Password</label><input id="password-input" name="password" type="text" min="6"/><br/>
      <input type="submit" value="REGISTER">
    </form>
    <h3><a href="/drugstore">Main page</a></h3>
  </body>
</html>
