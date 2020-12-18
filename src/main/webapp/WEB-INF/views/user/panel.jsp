<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User-panel</title>
</head>
<body>

<%@include file="../authentication.jsp" %>

<div style="margin: 5px;">
    <c:forEach items="${images}" var="image">
        <div style="padding: 5px">
            <img style="display: block; margin-left: auto; margin-right: auto; width: 500px" src="<c:url value="/resources/images/${image.path}" />" alt="image" />
        </div>
    </c:forEach>
</div>

</body>
</html>