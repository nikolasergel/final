<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<html>
<head>
    <title>TEST</title>
</head>
<body>
    <p>Roles</p>
    <h1>${lol}</h1>
    <p>${ roles }</p>
    <p>${ roles[0] }</p>
    <p>${ roles[0].toLowerCase() }</p>
    ${ roles[1].substring(2) }
</body>
</html>
