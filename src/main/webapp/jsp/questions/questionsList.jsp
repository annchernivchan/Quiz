<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Questions</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/styles.css"
          media="screen"/>
</head>
<body>
<h3 style="text-align: center">Questions List</h3>

<c:if test="${questions.size() == 0}">
    <div><em>No questions</em></div>
</c:if>
<c:if test="${questions.size() != 0}">
    <table border="1">
        <tr>
            <th align="center">Question</th>
            <th>Point</th>
            <th>Verified</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${questions}" var="question">
        <tr valign="top">
            <td><span>${question.questionText}</span></td>
            <td><span>${question.point}</span></td>
            <td align="center"><img
                    src="${pageContext.servletContext.contextPath}/resources/images/${question.verified ? 'verified' : 'not_verified'}.png"
                    class="imgIsVerified" name="verified"></td>
            <td><a href="${pageContext.servletContext.contextPath}/editQuestion?id=${question.id}">Edit</a></td>
            <td><a href="${pageContext.servletContext.contextPath}/deleteQuestion?id=${question.id}">Delete</a></td>
            </c:forEach>
    </table>
</c:if>
<input id="addButton" type="button" onclick="parent.location='/addQuestion'" value="Add question">
<input id="showTasks" type="button" onclick="parent.location='/tasks'" value="Show all tasks">
</body>
</html>
