<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Account</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
</head>
<body>

    <jsp:include page="_menuLoggedIn.jsp"></jsp:include>


    <div style="width: 30%" class="container p-0">
        <h3>Manage Account</h3>
        <%--<p style="color: red;">${errorMessage}</p>--%>
        <p style="color: dodgerblue;">${message}</p>
    </div>

    <div style="width: 30%" class="container bg-primary text-white p-3 mb-4 rounded">
        <form method="POST" onchange="enableSubmit()" action="${pageContext.request.contextPath}/secure/manageAccount">
            <input type="hidden" name="redirectId" value="${param.redirectId}" />
            <input type="hidden" name="type" value="1" />
            <div class="form-group">
                <label for="inputUserName">Username</label>
                <input class="form-control" id="inputUserName" type="text" placeholder="<%=request.getRemoteUser()%>" readonly>
            </div>
            <div class="form-group">
                <label for="inputName">Name</label>
                <input type="text" name="name" class="form-control form-control-sm" id="inputName"
                       aria-describedby="nameHelp" value="${name}" required>
            </div>
            <div class="form-group">
                <label for="inputSurname">Surname</label>
                <input type="text" name="surname" class="form-control form-control-sm" id="inputSurname"
                       aria-describedby="surnameHelp" value="${surname}" required>
            </div>
            <div class="form-group">
                <label for="inputEmail">Email</label>
                <input type="text" name="email" class="form-control form-control-sm" id="inputEmail"
                       aria-describedby="emailHelp" value="${email}" required>
            </div>
            <div class="form-group">
                <label for="inputDate">Date of Birth</label>
                <input type="date" name="dateOfBirth" class="form-control form-control-sm" id="inputDate"
                       aria-describedby="dateOfBirthHelp" value="${dateOfBirth}" required>
            </div>

            <button type="submit" id="save-changes-submit" class="btn btn-danger" disabled>Save changes</button>
        </form>
    </div>

    <div style="width: 30%" class="container p-0">
        <h3>Change Password</h3>
        <p style="color: red;">${errorMessage}</p>
    </div>
    <div style="width: 30%" class="container bg-primary text-white p-3 mb-5 rounded">
        <form method="POST" action="${pageContext.request.contextPath}/secure/manageAccount">
            <input type="hidden" name="redirectId" value="${param.redirectId}" />
            <input type="hidden" name="type" value="2" />
            <%--<h3>Change Password</h3>--%>
            <div class="form-group">
                <label for="inputOldPassword">Old Password</label>
                <input class="form-control" name="oldPassword" id="inputOldPassword" type="password" required>
            </div>
            <div class="form-group">
                <label for="inputNewPassword">New Password</label>
                <input type="password" name="newPassword" class="form-control form-control-sm" id="inputNewPassword" required>
            </div>
            <div class="form-group">
                <label for="inputConfirmPassword">Confirm New Password</label>
                <input type="password" name="confirmNewPassword" class="form-control form-control-sm" id="inputConfirmPassword" required>
            </div>

            <button type="submit" id="sumbit-button" class="btn btn-danger">Confirm</button>
        </form>
    </div>

    <jsp:include page="bootstrapBody.jsp"></jsp:include>
    <script src="../js/manageAccount.js"></script>
</body>
</html>
