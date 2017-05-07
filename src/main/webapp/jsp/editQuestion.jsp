<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit question</title>
    <link rel="stylesheet" type="text/css" href="../css/styles.css" media="screen"/>
</head>
<body>
<h3>
    Editing a question
</h3>
<p style="color: red;">${errorString}</p>
<form action="doEditQuestion?id=${question.id}" method="post">
    <table>
        <tr>
            <td><p>Question text: </p></td>
            <td><input type="text" name="questionText" value="${question.questionText}" maxlength="255"
                       id="fieldQuestionText"></td>
        </tr>
        <tr>
            <td><p>Point: </p></td>
            <td><input type="text" name="questionPoint" value="${question.point}" maxlength="6" id="fieldPoint"></td>
        </tr>
        <tr>
            <td><p>Question type: </p></td>
            <td><select name="questionType">
                <c:forEach items="${types}" var="type">
                    <option ${type.equals(question.questionType.name()) ? 'selected' : ''} value="${type}">${type}</option>
                </c:forEach>
            </select></td>
        </tr>
        <tr>
            <td><p>Answers: </p></td>
            <td>
                <table border="1">
                    <tr>
                        <th>Answer</th>
                        <th>Weight</th>
                    </tr>
                    <c:forEach items="${question.allAnswers}" var="answer">
                    <tr valign="top">
                        <td><input type="text" value="${answer.answerText}"></td>
                        <td><input type="text" value="${answer.weight}" id="fieldAnswerWeight"></td>
                        <td>
                            <a href="${pageContext.servletContext.contextPath}/questions/deleteAnswer?answerId=${answer.id}&questionId=${question.id}">Delete</a>
                        </td>
                        </c:forEach>
                </table>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Save"></td>
            <td><a href="${pageContext.servletContext.contextPath}/questions">Cancel</a></td>
        </tr>
    </table>
</form>
</body>
</html>
