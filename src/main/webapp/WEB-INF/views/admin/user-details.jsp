<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User-details</title>
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

<div>
    <div>Użytkownik</div>
    <div>${currentUser.email}</div>
    <div>Akcje</div>
    <div><a href="/admin/show-photos/${currentUser.id}">Pokaż zjęcia</a></div>
    <div><a href="/admin/add-photos/${currentUser.id}">Dodaj zjęcia</a></div>
    <div><a href="/admin/generate-pass/${currentUser.id}">Wygeneruj kod</a></div>
</div>

</body>
</html>