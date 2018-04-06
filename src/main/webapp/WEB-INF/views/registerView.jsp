<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
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
            <td><input type="text" name="name" required/> </td>
        </tr>
        <tr>
            <td>Surname</td>
            <td><input type="text" name="surname" required/> </td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="email" name="email" required/> </td>
        </tr>
        <tr>
            <td>User Name</td>
            <td><input type="text" name="username" required/> </td>
        </tr>
        <tr>
            <td>Date of Birth</td>
            <td><input type="date" name="dateOfBirth" required/> </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" required/> </td>
        </tr>
        <tr>
            <td>Confirm Password</td>
            <td><input type="password" name="confirmPassword" required/> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
