<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
    <link rel="stylesheet" href="../css/homeView.css" type="text/css">
</head>
<body onload="getPopularPosts()">

    <div class="content container-fluid p-0">
        <c:choose>
            <c:when test="<%=request.getRemoteUser() != null%>">
                <jsp:include page="_menuLoggedIn.jsp"></jsp:include>
            </c:when>
            <c:otherwise>
                <jsp:include page="_menu.jsp"></jsp:include>
            </c:otherwise>
        </c:choose>

        <%--<h6 class="ml-5">Home Page</h6>--%>

        <div class="row justify-content-center" style="margin-left: 0;margin-right: 0">
            <div class="container col-6">
                <form method="POST" action="${pageContext.request.contextPath}/home" class="card card-sm search-box">
                    <div class="card-body row no-gutters align-items-center" style="padding: 12px 24px;">
                        <div class="col-auto">
                            <%--<i class="icon-magnifying-glass h4 text-body"></i>--%>
                            <i class="fas fa-search fa-lg"></i>
                        </div>
                        <!--end of col-->
                        <div id="input-borderless" class="col">
                            <input class="form-control form-control-lg form-control-borderless" name="searchTerm" type="text" placeholder="Search for quotes..." required>
                        </div>
                        <!--end of col-->
                        <div class="col-auto">
                            <button class="btn btn-lg btn-primary" id="sumbit-button" type="submit">Search</button>
                        </div>
                        <!--end of col-->
                    </div>
                </form>
            </div>
            <!--end of col-->
        </div>

        <div class="card mx-5 mb-2 mt-5 text-center custom-center" style="background-color: rgb(176,224,230); max-width: 1000px">
            <div class="card-header p-2 pl-3"><h5>Popular Quotes</h5></div>
        </div>

        <div class="card mx-5 custom-center" style="max-width: 1000px">
            <div class="card-header">
                <div class="row" style="font-weight: bold; font-style: italic">
                    <div class="col-6">Quote</div>
                    <div class="col-3">Title</div>
                    <div class="col-3">Author</div>
                </div>
            </div>
            <div class="card-body p-0">
                <ul class="list-group-flush mb-0 p-0" id="postColumn">
                    <a id="genericPost" style="display: none" href="${pageContext.request.contextPath}/postDetails?id=" class="list-group-item list-group-item-action">
                        <div class="row">
                            <div class="col-6"><span id="quote"></span></div>
                            <div class="col-3"><span id="bookTitle"></span></div>
                            <div class="col-2"><span id="bookAuthor"></span></div>
                            <div class="col-1"><span id="score" class="mr-1"></span><i class="fas fa-fire"></i></div>
                        </div>
                    </a>
                </ul>
            </div>
        </div>
    </div>
    <jsp:include page="bootstrapBody.jsp"></jsp:include>
    <script src="../js/jquery/jquery-3.3.1.min.js"></script>
    <script src="../js/home.js"></script>
</body>
</html>
