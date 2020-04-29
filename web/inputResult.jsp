<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

</head>
<body>
<table border="1">
<c:forEach var="name" items="${names}">
    <tr>
        <th>${name}</th>
    </tr>
</c:forEach>
</table>
<a href="/inputName/remove">remove</a>...<a href="/inputName">input</a>

</body>
</html>