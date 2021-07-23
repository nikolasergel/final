<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="parts/nav.jsp"/>
<div class="d-flex justify-content-center" style="margin-top: 150px">
    <div class="col-3">
        <h3 class="text-center">Sign to Library</h3>
        <form>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" aria-describedby="emailHelp">
                <%--        <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>--%>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password">
            </div>
            <%--    <div class="mb-3 form-check">--%>
            <%--        <input type="checkbox" class="form-check-input" id="rememberme">--%>
            <%--        <label class="form-check-label" for="rememberme">Remember me</label>--%>
            <%--    </div>--%>
            <button type="submit" class="btn btn-primary me-2">Login</button>
            <a href="${pageContext.request.contextPath}?command=registration" class="btn btn-success">Create new account</a>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/view/parts/footer.jsp"/>

</body>
</html>
