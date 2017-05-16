<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/styles.css" media="screen">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/utils.js"></script><title>Tasks List</title>
</head>
<body>
<div class="container" style="width: 600px;">
<h3 class="text-center">Tasks List</h3>
    <table class="table table-bordered">
    <tr>
        <th class="col-sm-7 text-center">Task name</th>
        <th class="col-sm-3 text-center">Total point</th>
        <th class="col-sm-1"></th>
        <th class="col-sm-2"></th>
    </tr>
    <c:forEach items="${tasks}" var="task">
    <tr valign="top">
        <td class="text-center"><span>${task.name}</span></td>
        <td class="text-center"><span>${task.totalPoint}</span></td>
        <td><a class="btn btn-info" href="${pageContext.servletContext.contextPath}/editTask?id=${task.id}">Edit</a></td>
        <td><a class="btn btn-danger glyphicon glyphicon-trash" href="${pageContext.servletContext.contextPath}/deleteTask?id=${task.id}"></a></td>
        </c:forEach>
</table>
    <input class="btn btn-success" type="button" onclick="parent.location='/addTask'" value="Add task" >
    <input class="btn btn-default" type="button" onclick="parent.location='/'" value="Return to home page">

</div>
</body>
</html>
