<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="parts/nav.jsp"/>
<main>
    <div class="d-flex justify-content-center" style="margin-top: 150px">
        <div class="col-3">
            <h3 class="text-center">Sign to Library</h3>
            <form id="loginForm" name="loginForm" action="login" method="post">
                <div class="mb-3">
                    <label for="login" class="form-label">Email</label>
                    <input type="email" class="form-control" name="login" id="login" aria-describedby="emailHelp">
                    <%--        <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>--%>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" name="password" id="password">
                </div>
                <input type="hidden" name="command" value="LOGIN">
            </form>
            <div class="d-flex flex-row">
                <button type="submit" class="btn btn-primary me-2" form="loginForm">Login</button>
                <form name="registrationForm" action="registration" method="post">
                    <input type="hidden" name="command" value="GO_TO_REGISTRATION">
                    <button type="submit" class="btn btn-success">Registration</button>
                </form>
            </div>
        </div>
    </div>
</main>
<jsp:include page="/WEB-INF/view/parts/footer.jsp"/>

</body>
</html>
