<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Account</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
</head>
<body>

    <jsp:include page="_menuLoggedIn.jsp"></jsp:include>

    <h3>Manage Account</h3>

    <p style="color: red;">${errorMessage}</p>
    <p style="color: dodgerblue;">${message}</p>

    <form method="POST" action="${pageContext.request.contextPath}/secure/manageAccount" >
        <input type="hidden" name="redirectId" value="${param.redirectId}" />
        <input type="hidden" name="type" value="1" />
        <h2>${username}</h2>
        <table border="0">
            <tr>
                <td>Name</td>
                <td><input type="text" name="name" value= "${name}" /> </td>
            </tr>
            <tr>
                <td>Surname</td>
                <td><input type="text" name="surname" value= "${surname}" /> </td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="email" name="email" value= "${email}" /> </td>
            </tr>
            <tr>
                <td>Date of Birth</td>
                <td><input type="date" name="email" value= "${dateOfBirth}" /> </td>
            </tr>
            <tr>
                <td colspan ="2">
                    <input type="submit" value= "Save changes" />
                </td>
            </tr>
        </table>
    </form>
    <form method="POST" action="${pageContext.request.contextPath}/secure/manageAccount" >
        <input type="hidden" name="redirectId" value="${param.redirectId}" />
        <input type="hidden" name="type" value="2" />
        <h3>Change Password</h3>
        <table border="0">
            <tr>
                <td>Old Password</td>
                <td><input type="password" name="oldPassword" required/> </td>
            </tr>
            <tr>
                <td>New Password</td>
                <td><input type="password" name="newPassword" required/> </td>
            </tr>
            <tr>
                <td>Confirm New Password</td>
                <td><input type="password" name="confirmNewPassword" required/> </td>
            </tr>
            <tr>
                <td colspan ="2">
                    <input type="submit" value= "Confirm" />
                    <a href="${pageContext.request.contextPath}/">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
    <jsp:include page="bootstrapBody.jsp"></jsp:include>
</body>
</html>
