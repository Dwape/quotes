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
        <p style="color: red;">${errorMessage}</p>
    </div>

    <%--<form method="POST" action="${pageContext.request.contextPath}/secure/manageAccount" >
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
    </form>--%>
    <div style="width: 30%" class="container bg-primary text-white p-3 rounded">
        <form method="POST" action="${pageContext.request.contextPath}/secure/manageAccount">
            <input type="hidden" name="redirectId" value="${param.redirectId}" />
            <input type="hidden" name="type" value="1" />
            <div class="form-group">
                <label for="inputName">Username</label>
                <input class="form-control" type="text" placeholder="<%=request.getRemoteUser()%>" readonly>
            </div>
            <div class="form-group">
                <label for="inputName">Name</label>
                <input type="text" name="name" class="form-control form-control-sm" id="inputName"
                       aria-describedby="nameHelp" value="${name}">
            </div>
            <div class="form-group">
                <label for="inputSurname">Surname</label>
                <input type="text" name="surname" class="form-control form-control-sm" id="inputSurname"
                       aria-describedby="surnameHelp" value="${surname}">
            </div>
            <div class="form-group">
                <label for="inputEmail">Email</label>
                <input type="text" name="email" class="form-control form-control-sm" id="inputEmail"
                       aria-describedby="emailHelp" value="${email}">
            </div>
            <div class="form-group">
                <label for="inputDate">Date of Birth</label>
                <input type="date" name="dateOfBirth" class="form-control form-control-sm" id="inputDate"
                       aria-describedby="dateOfBirthHelp" value="${dateOfBirth}">
            </div>

            <button type="submit" class="btn btn-danger">Save changes</button>
        </form>
    </div>
    <div style="width: 30%" class="container my-1 pl-3"><p style="color: dodgerblue;">${message}</p></div>
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
                    <input type="submit" id="sumbit-button" value= "Confirm" />
                    <a href="${pageContext.request.contextPath}/">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
    <jsp:include page="bootstrapBody.jsp"></jsp:include>
</body>
</html>
