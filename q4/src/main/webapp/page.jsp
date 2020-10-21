<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Hello</title>
        <meta charset="UFF-8">
    </head>
    <body>
        <div>
            <a href="page2">Page2</a>
            <a href="bill">Bill</a>
        </div>
        <c:forEach items="${items}" var="item">
            <form method="POST">
                <span>${item}</span>
                <input type="hidden" name="item" value="${item}">
                <input type="submit" value="Purchase">
            </form>
        </c:forEach>

        <div>
            Total: ${total}
        </div>
    </body>
</html>
