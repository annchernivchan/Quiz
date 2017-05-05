<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Questions</title>
    <link rel="stylesheet" type="text/css" href="styles.css" media="screen" />
</head>
<body>
<h3 style="text-align: center">Question List</h3>
<table border="1">
    <tr>
        <td align="center">Question</td>
        <td>Point</td>
        <td></td>
        <td></td>
    </tr>
    <c:forEach items="${questions}" var="question" varStatus="status">
        <tr valign="top">
            <td>${question.getQuestionText()}</td>
            <td>${question.getPoint()}</td>
            <td><a href="${pageContext.servletContext.contextPath}/question/edit?id="${question.getId()}>Edit</a></td>
            <td><a href="${pageContext.servletContext.contextPath}/deleteQuestion.jsp?id="${question.getId()}">Delete</a></td>
    </c:forEach>
</table>
<input id="addButton" type="button" value="Add question">
</body>
</html>
