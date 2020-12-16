<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>
<body>

<form action="/login" method="post">

    <input type="email" name="username" placeholder="Email" />
    <input type="password" name="password" placeholder="Hasło" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button class="btn" type="submit">Zaloguj się</button>

</form>

</body>
</html>