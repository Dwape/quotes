<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
</head>
<body>

    <jsp:include page="_menu.jsp"></jsp:include>

    <%--<h3>Login Page</h3>

    <p style="color: red;">${errorMessage}</p>

    <form method="POST" action="${pageContext.request.contextPath}/login" >
        <input type="hidden" name="redirectId" value="${param.redirectId}" />
        <table border="0">
            <tr>
                <td>User Name</td>
                <td><input type="text" name="username" value= "${loginedUser.username}" required/> </td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" value= "${loginedUser.password}" required/> </td>
            </tr>

            <tr>
                <td colspan ="2">
                    <input type="submit" value= "Submit" />
                    <a href="${pageContext.request.contextPath}/">Cancel</a>
                </td>
            </tr>
        </table>
    </form>--%>

    <div style="width: 30%" class="container bg-primary text-white p-3 rounded">
        <form <%--method="POST" action="${pageContext.request.contextPath}/login"--%>>
            <div class="form-group">
                <%--<input type="hidden" name="redirectId" value="${param.redirectId}" />--%>
                <label for="exampleInputUsername">Username</label>
                <input type="text" class="form-control form-control-sm" id="exampleInputUsername" aria-describedby="usernameHelp" placeholder="Enter username">
                <%--<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" class="form-control form-control-sm" id="exampleInputPassword1" placeholder="Password">
            </div>
            <%--<div class="form-check">
                <input type="checkbox" class="form-check-input" id="exampleCheck1">
                <label class="form-check-label" for="exampleCheck1">Check me out</label>
            </div>--%>
            <button type="submit" class="btn btn-danger">Submit</button>
        </form>
    </div>


    <jsp:include page="bootstrapBody.jsp"></jsp:include>
</body>
</html>
