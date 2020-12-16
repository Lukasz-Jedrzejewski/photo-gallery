<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add-user-form</title>
</head>
<body>

<form:form modelAttribute="data" method="post" action="/admin/add-user">
    <div>
        <label>
            email <form:input path="userEmail"/>
        </label>
        <form:errors path="userEmail"/>
    </div>
    <div>
        <label>
            Nazwa galerii <form:input path="galleryName"/>
        </label>
        <form:errors path="galleryName"/>
    </div>
    <input type="submit" value="Zatwierdź"/>
</form:form>

</form>

</body>
</html>