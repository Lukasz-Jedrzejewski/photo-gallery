<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload-form</title>
</head>
<body>

<h3>Dodaj zdjęcia</h3>

<form method="post" action="/admin/add-photos" enctype="multipart/form-data">
    <input type="file" name="files" multiple required/>
    <button type="submit">Zapisz</button>
</form>

</body>
</html>