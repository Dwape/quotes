<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
    <link rel="stylesheet" href="../css/loginView.css">
</head>
<body>

    <jsp:include page="_menu.jsp"></jsp:include>

    <%--<div style="width: 30%" class="container bg-primary text-white p-3 rounded">
        <form method="POST" action="j_security_check">
            <div class="form-group">
                &lt;%&ndash;<input type="hidden" name="redirectId" value="${param.redirectId}" />&ndash;%&gt;
                <label for="exampleInputUsername">Username</label>
                <input type="text" name="j_username" class="form-control form-control-sm" id="exampleInputUsername" aria-describedby="usernameHelp" placeholder="Enter username" required>
                &lt;%&ndash;<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>&ndash;%&gt;
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" name="j_password" class="form-control form-control-sm" id="exampleInputPassword1" placeholder="Password">
            </div>
            &lt;%&ndash;<div class="form-check">
                <input type="checkbox" class="form-check-input" id="exampleCheck1">
                <label class="form-check-label" for="exampleCheck1">Check me out</label>
            </div>&ndash;%&gt;
            <button type="submit" class="btn btn-danger">Submit</button>
        </form>
    </div>--%>

    <div class="container">
        <div class="card card-container">
            <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
            <p id="profile-name" class="profile-name-card"></p>
            <form method="POST" action="j_security_check" class="form-signin">

                <label for="exampleInputUsername">Username</label>
                <input type="text" name="j_username" class="form-control form-control-sm" id="exampleInputUsername" aria-describedby="usernameHelp" placeholder="Enter username" required autofocus>

                <label for="exampleInputPassword1">Password</label>
                <input type="password" name="j_password" class="form-control form-control-sm" id="exampleInputPassword1" placeholder="Password" required>

                <button type="submit" class="btn btn-lg btn-primary btn-block btn-signin">Sign in</button>
            </form><!-- /form -->
        </div><!-- /card-container -->
    </div>


    <jsp:include page="bootstrapBody.jsp"></jsp:include>
</body>
</html>
