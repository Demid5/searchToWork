<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: demid5
  Date: 24.07.19
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Сорсинг кандидатов на работу</title>
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<h1>Сорсинг кандидатов на работу</h1>
<div id="blockCriteries">
<form action="/" method="POST">
    <p>В каких сайтах будем искать</p>
    <p><input type="checkbox" name="websait" value="HH.RU">HH.RU   <input type="checkbox" name="websait" value="SuperJob.ru"> SuperJob.ru   <input type="checkbox" name="websait" value="GorodRabot.ru">GorodRabot.ru</p>

    <br><br>
    Название профессии: <input name="profession" />
    <br><br>
    Образование: <select name="education">
    <option>не имеет значения</option>
    <option>высшее</option>
    <option>среднее</option>
</select>
    <br><br>
    Опыт работы: <select name="experience">
    <option>не имеет значения</option>
    <option>нет опыта</option>
    <option>От 1 года до 3 лет</option>
    <option>От 3 до 6 лет</option>
    <option>Более 6 лет</option>
</select>

    <br><br>
    Ключевые навыки: <input name="key_skills" />
    <br><br>
    <p><input type="submit" value="Поиск"></p>
    <br><br>


</form>
</div>

<div id="resultLink">
    <p id="textResultLinks"> Список кандидатов на работу по заданным критериям: </p>
    <ol>
        <c:forEach var="per" items="${resultList}">
            <li> <a href=${per}>${per}</a> </li>
        </c:forEach>
    </ol>
</div>

</body>
</html>
