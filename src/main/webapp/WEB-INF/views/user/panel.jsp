<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User-panel</title>
</head>
<body>

<%@include file="../authentication.jsp" %>

<div>
    <c:forEach items="${images}" var="image">
        <div>
            <img src="<c:url value="/resources/images/${image.path}" />" alt="image" />
        </div>
    </c:forEach>
</div>

</body>
</html>