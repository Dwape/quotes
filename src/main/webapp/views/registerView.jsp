<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="../css/register.css">
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
</head>
<body>

    <jsp:include page="_menu.jsp"></jsp:include>

    <div class="container p-0 d-flex justify-content-start header">
        <div class="col p-0">
            <h3>Register</h3>
            <p style="color: red;">${errorMessage}</p>
        </div>
    </div>

    <div class="container">
        <div class="card card-container">
            <form method="POST" action="${pageContext.request.contextPath}/register">
                <input type="hidden" name="redirectId" value="${param.redirectId}" />

                <div class="form-group">
                    <label for="name">Name</label>
                    <input class="form-control" name="name" id="name" type="text" required>
                </div>
                <div class="form-group">
                    <label for="surname">Surname</label>
                    <input type="text" name="surname" class="form-control form-control-sm" id="surname" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="text" name="email" class="form-control form-control-sm" id="email" required>
                </div>
                <div class="form-group">
                    <label for="username">Username</label>
                    <input class="form-control" name="username" id="username" type="text" required>
                </div>
                <div class="form-group">
                    <label for="dateOfBirth">Date of Birth</label>
                    <input type="date" name="dateOfBirth" class="form-control form-control-sm" id="dateOfBirth" required>
                </div>
                <div class="form-group">
                    <label for="password">New Password</label>
                    <input type="password" name="password" class="form-control form-control-sm" id="password" required>
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Confirm Password</label>
                    <input type="password" name="confirmPassword" class="form-control form-control-sm" id="confirmPassword" required>
                </div>

                <button type="submit" id="register-submit" class="btn btn-lg btn-primary btn-block btn-signin">Submit</button>
                <div class="my-3"><a href="${pageContext.request.contextPath}/index">Cancel</a></div>
            </form>
        </div>
    </div>


    <jsp:include page="bootstrapBody.jsp"></jsp:include>
</body>
</html>
