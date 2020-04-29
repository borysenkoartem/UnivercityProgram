<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
</head>
<body>
<table border="1">

    <c:forEach var="i" begin="0" end="9">
        <tr>
            <c:forEach var="j" begin="0" end="9">
                <c:choose>
                    <c:when test="${i == 0 && j == 0}">
                        <th></th>
                    </c:when>
                    <c:when test="${i == 0}">
                        <th>${j}</th>
                    </c:when>
                    <c:when test="${j == 0}">
                        <th>${i}</th>
                    </c:when>
                    <c:otherwise>
                        <th>${i * j}</th>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </c:forEach>

</table>
</body>
</html>