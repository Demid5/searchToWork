<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: demid5
  Date: 24.07.19
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>User Info</title>
</head>
<body>
<p>Name: <%= request.getParameter("username") %></p>
<p>Country: <%= request.getParameter("country") %></p>
<p>Gender: <%= request.getParameter("gender") %></p>
<h4>Selected courses</h4>
<ul>
        <c:forEach items="var" var="courses">
        <tr>
        <li><p>Results:  <%=request.getParameterValues("courses")%> </p></li>
        </tr>
        </c:forEach>


</ul>
</body>
</html>
