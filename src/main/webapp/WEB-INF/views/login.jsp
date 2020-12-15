<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
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