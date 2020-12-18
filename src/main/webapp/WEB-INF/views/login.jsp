<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>
<body>
<div>
    <form action="/login" method="post">
        <div>
            <input type="email" name="username" placeholder="Email" />
        <div>
        <div>
            <input type="password" name="password" placeholder="Hasło" />
        <div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="btn" type="submit">Zaloguj się</button>
    </form>
</div>
</body>
</html>