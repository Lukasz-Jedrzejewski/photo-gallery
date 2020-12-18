<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User-details</title>
</head>
<body>

<%@include file="../authentication.jsp" %>

<a href="/admin/panel">panel</a>

<div>
    <div>Użytkownik</div>
    <div>${currentUser.email}</div>
    <div>Dodanych zdjęć: ${imagesSize}</div>
    <div>Akcje</div>
    <div><a href="/admin/show-photos/${currentUser.id}">Pokaż zjęcia</a></div>
    <div><a href="/admin/add-photos/${currentUser.id}">Dodaj zjęcia</a></div>
    <div><a href="/admin/generate-pass/${currentUser.id}">Wygeneruj kod</a></div>
</div>

</body>
</html>