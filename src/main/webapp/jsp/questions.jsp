<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Questions</title>
    <link rel="stylesheet" type="text/css" href="../css/styles.css" media="screen" />
</head>
<body>
<h3 style="text-align: center">Questions List</h3>
<table border="1">
    <tr>
        <th align="center">Question</th>
        <th>Point</th>
        <td></td>
        <td></td>
    </tr>
    <c:forEach items="${questions}" var="question">
        <tr valign="top">
            <td>${question.questionText}</td>
            <td>${question.point}</td>
            <td><a href="${pageContext.servletContext.contextPath}/editQuestion?id=${question.id}">Edit</a></td>
            <td><a href="${pageContext.servletContext.contextPath}/deleteQuestion?id=${question.id}">Delete</a></td>
    </c:forEach>
</table>
<input id="addButton" type="button" value="Add question">
</body>
</html>
