<%@ page import="static com.sun.tools.doclint.Entity.lang" %><%--
  Created by IntelliJ IDEA.
  User: demid5
  Date: 24.07.19
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Form</title>
</head>
<body>
<form action="/" method="POST">
    Name: <input name="username" />
    <br><br>
    Gender: <input type="radio" name="gender" value="female" checked />Female
    <input type="radio" name="gender" value="male" />Male
    <br><br>
    Country: <select name="country">
    <option>Iran</option>
    <option>Turkey</option>
    <option>China</option>
    <option>Germany</option>
</select>
    <br><br>
    Courses:
    <input type="checkbox" name="courses" value="JavaSE" checked />Java SE
    <input type="checkbox" name="courses" value="JavaFX" checked />Java FX
    <input type="checkbox" name="courses" value="JavaEE" checked />Java EE
    <br><br>
    <input type="submit" value="Submit" />




    <p>Name: <%= request.getParameter("username") %></p>
    <p>Country: <%= request.getParameter("country") %></p>
    <p>Gender: <%= request.getParameter("gender") %> </p>
</form>
</body>
</html>
