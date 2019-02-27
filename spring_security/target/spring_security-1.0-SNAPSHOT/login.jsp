<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--method必须为post  username password固定名称-->
<form method="post" action="${pageContext.request.contextPath}/login">
    <input type="text" name="username"></br>
    <input type="text" name="password"></br>
    <input type="submit" value="登录">
</form>
</body>
</html>