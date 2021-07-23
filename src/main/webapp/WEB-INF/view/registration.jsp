<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/parts/nav.jsp"/>
<div class="d-flex justify-content-center" style="margin-top: 50px">
    <div class="col-3">
        <h3 class="text-center">Create your account</h3>
        <form>
            <div class="bla">
                <label for="first_name" class="form-label">First name</label>
                <input type="text" class="form-control" id="first_name">
            </div>
            <div class="bla">
                <label for="last_name" class="form-label">Last name</label>
                <input type="text" class="form-control" id="last_name">
            </div>
            <div class="bla">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username">
            </div>
            <div class="bla">
                <label for="email" class="form-label">Email address</label>
                <input type="email" class="form-control" id="email">
            </div>
            <div class="bla">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password">
            </div>
            <div class="bla mb-2">
                <label for="confirm_password" class="form-label">Confirm password</label>
                <input type="password" class="form-control" id="confirm_password">
            </div>
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/view/parts/footer.jsp"/>
</body>
</html>
