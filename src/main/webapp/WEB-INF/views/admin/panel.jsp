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
    <c:forEach items="${users}" var="user">
        ${user.email}
    <a href="/admin/add-photos/${user.id}">Dodaj zjęcia</a>
    <c:if test="${user.password == null}">
        <p>Dodaj zdjęcia by wygenerować kod</p>
    </c:if>
    <c:if test="${user.password != null}">
        <a href="/admin/generate-pass/${user.id}">Wygeneruj kod</a>
    </c:if>
    </c:forEach>
</div>

</body>
</html>