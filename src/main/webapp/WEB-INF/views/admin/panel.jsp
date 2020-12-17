<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>
<body>

<sec:authorize access="isAuthenticated()">
    <p>Zalogowany jako: ${user.email}</p>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <form action="<c:url value="/logout"/>" method="post">
        <input type="submit" value="Wyloguj">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</sec:authorize>

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