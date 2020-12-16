<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add-user-form</title>
</head>
<body>

<form:form modelAttribute="user" method="post" action="/admin/add-user">
    <form:hidden path="id"/>
    <div>
        <label>
            email <form:input path="email"/>
        </label>
        <form:errors path="email"/>
    </div>
    <input type="submit" value="Zatwierdź"/>
</form:form>

</form>

</body>
</html>