<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<ul class="pageNavigation">
    <form>
        <input name="command" type="hidden" value="drugs"/>
        <p>Number of display items per page:</p>
        <div>
            <input type="radio" id="id5"
                   name="limit" value="5">
            <label for="id5">5</label>

            <input type="radio" id="id10"
                   name="limit" value="10">
            <label for="id10">10</label>

            <input type="radio" id="id15"
                   name="limit" value="15">
            <label for="id15">15</label>
        </div>
        <div>
            <button type="submit">Submit</button>
        </div>
    </form>


    <c:if test="${requestScope.currentPage != 1}">
        <a href="controller?command=drugs&page=1&limit=${requestScope.limit}">First</a>
        <a href="controller?command=drugs&page=${requestScope.currentPage - 1}&limit=${requestScope.limit}">Prev</a>
    </c:if>

    Page ${requestScope.currentPage} out of ${requestScope.totalPages}

    <c:if test="${requestScope.currentPage != requestScope.totalPages}">
        <a href="controller?command=drugs&page=${requestScope.currentPage + 1}&limit=${requestScope.limit}">Next</a>
        <a href="controller?command=drugs&page=${requestScope.totalPages}&limit=${requestScope.limit}">Last</a>
    </c:if>
</ul>