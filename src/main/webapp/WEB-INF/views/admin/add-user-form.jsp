<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add-user-form</title>
</head>
<body>

<%@include file="../authentication.jsp" %>

<a href="/admin/panel">panel</a>

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