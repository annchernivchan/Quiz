<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit question</title>
</head>
<body>
<form action="" method="post">
    <p>Question id</p> <input type="text" value="${question.getId()}">
    <p>Question text</p> <input type="text" value="${question.getQuestionText()}">
    <p>Answers</p>
    <table border="1">
        <tr>
            <td>Answer</td>
            <td>Weight</td>
        </tr>
        <c:forEach items="${questions.getAllAnswers()}" var="answer" varStatus="status">
        <tr valign="top">
            <td>${answer.getAnswerText()}</td>
            <td>${answer.getWeight()}</td>
            <%--<td><a href="editQuestion.jsp">Edit</a></td>--%>
            <%--<td><a href="deleteQuestion.jsp">Delete</a></td>--%>
            </c:forEach>
    </table>

</form>
</body>
</html>
