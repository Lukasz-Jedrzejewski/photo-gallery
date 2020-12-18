<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload-form</title>
</head>
<body>

<%@include file="../authentication.jsp" %>

<h3>Dodaj zdjęcia</h3>
<h5>Dopuszczalne rozszerzenia: .jpg .tiff .bmp .png</h5>
<h5>Pliki z innym rozszerzeniem nie zostaną zapisane</h5>

<form:form modelAttribute="gallery" method="post" action="/admin/add-photos" enctype="multipart/form-data">
    <form:hidden path="id"/>
    <form:hidden path="name"/>
    <form:hidden path="user"/>
    <input type="file" name="files" accept="image/png, image/jpeg, image/jpg, image/tiff, image/bmp" multiple required/>
    <button type="submit">Zapisz</button>
</form:form>

</body>
</html>