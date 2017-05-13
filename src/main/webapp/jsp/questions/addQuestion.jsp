<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/styles.css" media="screen"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/utils.js"></script>
    <title>Add question</title>
</head>
<body>
<h3>
    Adding a question
</h3>
<form id="formSaveQuestion" action="doAddQuestion" method="post">
    <table>
        <tr>
            <td><p>Question text: </p></td>
            <td><input type="text" name="questionText" value="${question.questionText}" maxlength="255"
                       id="fieldQuestionText"></td>
            <td><em style="color: red;">${questionTextError}</em></td>
        </tr>
        <tr>
            <td><p>Point: </p></td>
            <td><input type="text" class="fieldQuestionPoint" name="questionPoint" value="${question.questionPoint}"
                       maxlength="6"></td>
            <td><em style="color: red;">${questionPointError}</em></td>
        </tr>
        <tr>
            <td><p>Question type: </p></td>
            <td><select name="questionType">
                <c:forEach items="${types}" var="type">
                    <option value="${type}">${type}</option>
                </c:forEach>
            </select></td>
        </tr>
        <tr>
            <td><p>Answers: </p></td>
            <td id="answersTableTd">
                <em>No answers</em>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <button id="addAnswer">Add answer</button>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" class="saveQuestion" value="Save">
                <a href="${pageContext.servletContext.contextPath}/questions">Cancel</a>
            </td>
        </tr>
    </table>
</form>
</body>

</html>
