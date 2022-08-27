<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty param.language}">
  <c:set var="language" value="${param.language}" scope="session"/>
</c:if>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" />

<html>
  <head>
    <title>DrugStore</title>
  </head>
  <body>
  <jsp:include page="jsp/language.jsp"/>
  <jsp:include page="jsp/navbar.jsp"/>
<c:if test="${sessionScope.client == null}">
  <h1><fmt:message key="message.main.welcome"/></h1>
</c:if>
<c:if test="${sessionScope.client != null}">
  <h1><fmt:message key="message.main.welcome"/>, ${sessionScope.client.firstName} ${sessionScope.client.lastName} !!!</h1>
</c:if>
    <ul>
      <li><a href="controller?command=clients"><fmt:message key="message.navbar.all_clients"/></a></li>
      <li><a href="controller?command=pharmacists"><fmt:message key="message.navbar.pharmacists"/></a></li>
      <li><a href="controller?command=create_drug_form"><fmt:message key="message.navbar.add_new_drug"/></a></li>
    </ul>
  </body>
</html>
