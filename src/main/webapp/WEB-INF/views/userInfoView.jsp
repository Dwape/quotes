<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>

<jsp:include page="_menu.jsp"></jsp:include>

<h3>Hello: ${loggedInUser.username}</h3>

User Name: <b>${loggedInUser.username}</b>
<br />
<%--Gender: ${loginedUser.gender } <br />--%>


</body>
</html>