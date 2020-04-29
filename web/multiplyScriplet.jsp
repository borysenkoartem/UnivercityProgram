<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

</head>
<body>
<table border="1">

    <% for (int i = 0; i < 10; i++) { %>
    <tr>
        <% for (int j = 0; j < 10; j++) { %>
        <% if (i == 0 && j == 0) { %>
        <th></th>
        <% } else if (i == 0) { %>
        <th><%=j%></th>
        <% } else  if (j == 0) { %>
        <th><%=i%></th>
        <% } else {%>
        <th><%=i*(j)%></th>
        <% } %>
        <% } %>
    </tr>
    <% } %>

</table>
</body>
</html>