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

    <div id="nav">
        <ul>
            <li><a href="/admin/has-a-gallery">Użytkownicy posiadający galerię</a> </li>
            <li><a href="/admin/does-not-have-a-gallery">Użytkownicy nie posiadający galerii</a></li>
        </ul>
    </div>

<a href="/admin/add-user">Dodaj używtkownika</a>

</body>
</html>