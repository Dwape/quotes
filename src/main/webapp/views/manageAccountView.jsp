<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Account</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
    <link rel="stylesheet" href="../css/manageAccountView.css">
</head>
<body>

    <jsp:include page="_menuLoggedIn.jsp"></jsp:include>


    <div class="container p-0 d-flex justify-content-start header">
        <div class="col">
            <h3>Manage Account</h3>
            <%--<p style="color: red;">${errorMessage}</p>--%>
            <p style="color: black;">${message}</p>
            <p style="color: black;">${errorMessage}</p>
        </div>
    </div>

    <div class="container">
        <div class="card card-container">
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

                <a class="badge badge-secondary" data-toggle="modal" data-target="#citeModal">Change password</a>

                <button type="submit" id="save-changes-submit" class="btn btn-lg btn-primary btn-block btn-signin" disabled>Save changes</button>
            </form><!-- /form -->

                <%--Modal--%>
                <div class="modal fade" id="citeModal" tabindex="-1" role="dialog" aria-labelledby="citeModalTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="citeModalTitle">Change Password</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form method="POST" action="${pageContext.request.contextPath}/secure/manageAccount">
                                <div class="modal-body">

                                    <input type="hidden" name="redirectId" value="${param.redirectId}" />
                                    <input type="hidden" name="type" value="2" />

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
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary" id="save-btn">Save</button>
                                </div>
                            </form><!-- /form -->
                        </div>
                    </div>
                </div>
                <%-- / Modal--%>
        </div><!-- /card-container -->
    </div>

    <%--<div style="width: 30%" class="container p-0">
        <h3>Change Password</h3>
        <p style="color: red;">${errorMessage}</p>
    </div>

    <div class="container">
        <div class="card card-container">
            <form method="POST" action="${pageContext.request.contextPath}/secure/manageAccount">

                <input type="hidden" name="redirectId" value="${param.redirectId}" />
                <input type="hidden" name="type" value="2" />

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

                <button type="submit" id="sumbit-button" class="btn btn-lg btn-primary btn-block btn-signin">Confirm</button>
            </form><!-- /form -->
        </div><!-- /card-container -->
    </div>--%>


    <jsp:include page="bootstrapBody.jsp"></jsp:include>
    <script src="../js/manageAccount.js"></script>
</body>
</html>
