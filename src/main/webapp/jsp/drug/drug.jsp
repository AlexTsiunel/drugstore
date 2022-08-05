<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Drug</title>
  </head>
  <body>
    <h1>The Drug</h1>
    <h3>Id</h3>
    <p>${drug.id}</p>
    <h3>Name</h3>
    <p>${drug.name}</p>
    <h3>Release Form</h3>
    <p>${drug.releaseForm}</p>
    <h3>Price</h3>
    <p>${drug.price}</p>
    <h3><a href="controller?command=drugs">All drugs</a></h3>
  </body>
</html>
