<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload-form</title>
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

<h3>Dodaj zdjęcia</h3>
<h5>Dopuszczalne rozszerzenia: .jpg .tiff .bmp .png</h5>

<form:form modelAttribute="gallery" method="post" action="/admin/add-photos" enctype="multipart/form-data">
    <form:hidden path="id"/>
    <form:hidden path="name"/>
    <form:hidden path="user"/>
    <input type="file" name="files" accept="image/png, image/jpeg, image/jpg, image/tiff, image/bmp" multiple required/>
    <button type="submit">Zapisz</button>
</form:form>

</body>
</html>