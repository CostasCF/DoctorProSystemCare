<%--
  Created by IntelliJ IDEA.
  User: COSTAS
  Date: 6/4/2021
  Time: 5:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout</title>
</head>
<body>
<% session.removeAttribute("username");
    session.removeAttribute("password");
    session.invalidate();
    %>
<h1>Logout was successful</h1>
</body>
</html>
