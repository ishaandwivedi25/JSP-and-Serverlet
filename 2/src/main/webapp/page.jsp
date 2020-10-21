<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Hello</title>
        <meta charset="UFF-8">

        <script>
            function showNext(id) {
                console.log(id);
                var element = document.querySelector('#q'+id.toString())
                element.nextElementSibling.style = "display: block";
                console.log(element.nextElementSibling)
            }
        </script>
    </head>
    <body>
        <div>
            <form method="POST">
                <c:forEach items="${questions}" var="question" varStatus="outloop">
                    <c:if test="${outloop.index == 0}">
                    <div id="q${question.id}" class="question">
                    </c:if>
                    <c:if test="${outloop.index > 0}">
                    <div id="q${question.id}" class="question" style="display: none">
                    </c:if>
                        <span>${question.question}</span><br>
                        <c:forEach items="${question.choices}" var="choice" varStatus="loop">
                            <input type="radio" id="${question.id}-${loop.index}" name="${question.id}" value="${loop.index}" onclick="showNext(${question.id})">
                            <label for="${question.id}-${loop.index}">${choice}</label><br>
                        </c:forEach>
                    </div>
                </c:forEach>
                <div class="question" style="display: none">
                    <input type="submit" value="Submit">
                </div>
            </form>
        </div>
        <div>
            <c:if test="${not empty result}">
                ${result}
            </c:if>
        </div>
    </body>
</html>