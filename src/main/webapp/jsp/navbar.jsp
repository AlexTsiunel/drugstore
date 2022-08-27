<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" />

<ul class="navbar">
    <li><a href="controller?command=home"><fmt:message key="message.navbar.home"/></a> </li>
    <c:if test="${sessionScope.client != null}">
        <li><a href="controller?command=logout"><fmt:message key="message.navbar.logout"/></a> </li>
    </c:if>
    <c:if test="${sessionScope.client == null}">
        <li><a href="controller?command=create_client_form"><fmt:message key="message.navbar.sing_up"/></a> </li>
        <li><a href="controller?command=login_form"><fmt:message key="message.navbar.sing_in"/></a> </li>
    </c:if>
    <li><a href="controller?command=show_cart"><fmt:message key="message.navbar.cart"/></a> </li>
    <li><a href="controller?command=drugs"><fmt:message key="message.navbar.all_drugs"/></a> </li>
    <li><a href="controller?command=clients"><fmt:message key="message.navbar.all_clients"/></a> </li>
    <li><a href="controller?command=orders"><fmt:message key="message.navbar.all_orders"/></a> </li>
</ul>