<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
</head>
<body>

    <jsp:include page="_menu.jsp"></jsp:include>

    <h3>Register</h3>

    <p style="color: red;">${errorMessage}</p>

    <form method="POST" action="${pageContext.request.contextPath}/register">
        <input type="hidden" name="redirectId" value="${param.redirectId}" />
        <table border="0">
            <tr>
                <td>Name</td>
                <td><input type="text" name="name" value= "${loginedUser.name}" required/> </td>
            </tr>
            <tr>
                <td>Surname</td>
                <td><input type="text" name="surname" value= "${loginedUser.surname}" required/> </td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="email" name="email" value= "${loginedUser.email}" required/> </td>
            </tr>
            <tr>
                <td>User Name</td>
                <td><input type="text" name="username" value= "${loginedUser.username}" required/> </td>
            </tr>
            <tr>
                <td>Date of Birth</td>
                <td><input type="date" name="dateOfBirth" value= "${loginedUser.dateOfBirth}" required/> </td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" value= "${loginedUser.password}" required/> </td>
            </tr>
            <tr>
                <td>Confirm Password</td>
                <td><input type="password" name="confirmPassword" <%--value= "${loginedUser.confirmPassword}"--%> required/> </td>
            </tr>
            <tr>
                <td colspan ="2">
                    <input type="submit" value= "Submit" />
                    <a href="${pageContext.request.contextPath}/">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
    <jsp:include page="bootstrapBody.jsp"></jsp:include>
</body>
</html>
