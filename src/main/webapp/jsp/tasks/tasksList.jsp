<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/styles.css" media="screen">
    <title>Tasks List</title>
</head>
<body>
<h3>Tasks List</h3>
<table border="1">
    <tr>
        <th align="center">Task name</th>
        <th>Total point</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${tasks}" var="task">
    <tr valign="top">
        <td>${task.name}</td>
        <td>${task.totalPoint}</td>
        <td><a href="${pageContext.servletContext.contextPath}/editTask?id=${task.id}">Edit</a></td>
        <td><a href="${pageContext.servletContext.contextPath}/deleteTask?id=${task.id}">Delete</a></td>
        </c:forEach>
</table>
<input id="addButton" type="button" onclick="parent.location='/addTask'" value="Add task" class="addButton">
</body>
</html>
