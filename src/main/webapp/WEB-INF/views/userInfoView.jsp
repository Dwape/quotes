<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
</head>
<body>

    <jsp:include page="_menuLoggedIn.jsp"></jsp:include>

    <h3>Hello: ${username}</h3>

    User Name: <b>${username}</b>
    <br />
    <%--Gender: ${loginedUser.gender } <br />--%>

    <jsp:include page="bootstrapBody.jsp"></jsp:include>
</body>
</html>