<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

<jsp:include page="_menu.jsp"></jsp:include>

<h3>Login Page</h3>

<p style="color: red;">${errorMessage}</p>

<form method="POST" action="${pageContext.request.contextPath}/login">
    <input type="hidden" name="redirectId" value="${param.redirectId}" />
    <table border="0">
        <tr>
            <td>User Name</td>
            <td><input type="text" name="username" value= "${userAccount.username}" /> </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" value= "${userAccount.password}" /> </td>
        </tr>

        <tr>
            <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>
    </table>
</form>

<p style="color:blue;">Login with:</p>

Giansco/352312 <br>
Gengu/afdafa



</body>
</html>
