<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 6/5/2021
  Time: 4:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h2>
<%
   response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  response.setHeader("Pragma", "no-cache"); // HTTP 1
    response.setHeader("Expires", "0");

    if(session.getAttribute("username") == null)
        response.sendRedirect("login.jsp");
%>

    Welcome <% session.getAttribute("username"); %>
</h2>
<a href="showDetails.jsp">Show details</a>
<a href="logout.jsp">Logout</a>
</body>
</html>
