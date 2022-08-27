<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<ul class="language">
    <form>
        <input name="command" type="hidden" value="change_language"/>
        <div>
            <input type="radio" id="idEn"
                   name="language" value="en">
            <label for="idEn">en</label>

            <input type="radio" id="idRu"
                   name="language" value="ru">
            <label for="idRu">ru</label>
        </div>
        <div>
            <button type="submit">Submit</button>
        </div>
    </form>
</ul>