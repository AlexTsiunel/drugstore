<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<ul class="navbar">
    <li><a href="controller?command=home">Home</a> </li>
    <c:if test="${sessionScope.client != null}">
        <li><a href="controller?command=logout">Logout</a> </li>
    </c:if>
    <c:if test="${sessionScope.client == null}">
        <li><a href="controller?command=create_client_form">Sing up</a> </li>
        <li><a href="controller?command=login_form">Sing in</a> </li>
    </c:if>
    <li><a href="controller?command=show_cart">Cart</a> </li>
    <li><a href="controller?command=drugs">All Drugs</a> </li>
    <li><a href="controller?command=clients">All Users</a> </li>
    <li><a href="controller?command=orders">All Orders</a> </li>
</ul>