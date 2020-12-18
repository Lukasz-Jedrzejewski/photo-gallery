<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin-panel</title>
</head>
<body>

<%@include file="../authentication.jsp" %>

<a href="/admin/add-user">Dodaj używtkownika</a>

<h3>Lista Użytkowników</h3>
<div>

<table>
    <tr>
        <th>Użytkownik</th>
        <th>Akcje</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.email}</td>
            <td>
                <a href="/admin/user-details/${user.id}">szczegóły</a>
            </td>
        </tr>
    </c:forEach>
</table>
</div>

</body>
</html>