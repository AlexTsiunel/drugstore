<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Pharmacist</title>
  </head>
  <body>
    <h1>The Pharmacist</h1>
    <h3>Id</h3>
    <p>${pharmacist.id}</p>
    <h3>First Name</h3>
    <p>${pharmacist.firstName}</p>
    <h3>Last Name</h3>
    <p>${pharmacist.lastName}</p>
    <h3>Email</h3>
    <p>${pharmacist.email}</p>
    <h3><a href="controller?command=pharmacists">All pharmacists</a></h3>
  </body>
</html>
