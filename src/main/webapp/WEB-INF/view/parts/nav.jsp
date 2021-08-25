<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/main.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous">
</script>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <%--        <div class="container-fluid">--%>
    <a class="navbar-brand" href="${pageContext.request.serverName}?command=get_home">Library</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02"
            aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
                <form name="go_to_home" action="home" method="post">
                    <input type="hidden" name="command" value="go_to_home"/>
                    <button type="submit" class="nav-link remove_button_css">Home</button>
                </form>
<%--<a class="nav-link" href="${pageContext.request.contextPath}/${pageContext.request.httpServletMapping}?command=go_to_home">Home</a>--%>
            </li>
            <li class="nav-item me-2">
                <form name="go_to_login" action="login" method="post">
                    <input type="hidden" name="command" value="go_to_login"/>
                    <button type="submit" class="nav-link remove_button_css">Login</button>
                </form>
                <%--                        <a class="nav-link" href="${pageContext.request.contextPath}/${pageContext.request.httpServletMapping}?command=go_to_login">Login</a>--%>
            </li>
        </ul>
        <form class="d-flex">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
    </div>
    <%--        </div>--%>
</nav>
