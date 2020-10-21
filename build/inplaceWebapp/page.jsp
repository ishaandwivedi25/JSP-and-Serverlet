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
            <form method="POST">
                <input type="text" name="username" placeholder="Username">
                <input type="password" name="password" placeholder="password">

                <input type="submit" value="Log In">
            </form>
        </div>
        <div>
            <c:if test="${not empty error}">
                Oh mannnn, wrong!
            </c:if>
            <c:if test="${not empty validated}">
                Yay!
            </c:if>
        </div>
    </body>
</html>